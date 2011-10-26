package cms.model.dao;

import cms.model.model.TagEntity;
import cms.model.meta.TagEntityMeta;
import cms.model.model.PageTagEntity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Transaction;
import java.util.ConcurrentModificationException;
import java.util.List;
import org.slim3.datastore.Datastore;
import org.slim3.datastore.EntityNotFoundRuntimeException;

public class TagDAO implements DAO {

	private TagEntityMeta meta = TagEntityMeta.get();

	public List<TagEntity> getAll() {
		List<TagEntity> tagEntities = Datastore.query(meta).sort(meta.name.asc).asList();
		return tagEntities;
	}

	public TagEntity get(Key key) {
		TagEntity tagEntity = null;
		try {
			tagEntity = Datastore.get(TagEntity.class, key);
		} catch (EntityNotFoundRuntimeException e) {
			return null;
		}

		return tagEntity;
	}

	public TagEntity getByName(String name) {
		TagEntity tagEntity = Datastore.query(meta).filter(meta.name.equal(name)).asSingle();
		return tagEntity;
	}

	public TagEntity insert(TagEntity tagEntity) {
		Transaction tx = Datastore.beginTransaction();
		Datastore.put(tagEntity);
		tx.commit();

		return tagEntity;
	}

	public TagEntity edit(TagEntity tagEntity) throws ConcurrentModificationException {
		Transaction tx = Datastore.beginTransaction();
		try {
			Datastore.get(tx, TagEntity.class, tagEntity.getKey(), tagEntity.getVersion());

			Datastore.put(tx, tagEntity);
			tx.commit();
		} catch (ConcurrentModificationException e) {
			if (tx.isActive()) {
				tx.rollback();
			}
			throw e;
		}

		return tagEntity;
	}

	public void delete(Key key, Long version) throws ConcurrentModificationException {
		Transaction tx = Datastore.beginTransaction();
		try {
			TagEntity tagEntity = Datastore.get(tx, meta, key, version);
			for (PageTagEntity pageTagEntity : tagEntity.getPageTagListRef().getModelList()) {
				Datastore.deleteWithoutTx(pageTagEntity.getKey());
			}

			Datastore.delete(tx, tagEntity.getKey());
			tx.commit();
		} catch (ConcurrentModificationException e) {
			if (tx.isActive()) {
				tx.rollback();
			}
			throw e;
		}
	}
}
