package cms.model.dao;

import cms.model.model.TagEntity;
import cms.model.meta.TagEntityMeta;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Transaction;
import java.util.List;
import org.slim3.datastore.Datastore;

public class TagDAO implements DAO {
	private TagEntityMeta meta = TagEntityMeta.get();
	
	public List<TagEntity> getAll() {
		List<TagEntity> tagEntities = Datastore.query(meta).sort(meta.name.desc).asList();
		return tagEntities;
	}

	public TagEntity get(Key key) {
		TagEntity tagEntity = Datastore.get(TagEntity.class, key);
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

	public TagEntity edit(TagEntity tagEntity) {
		Transaction tx = Datastore.beginTransaction();
		Datastore.put(tagEntity);
		tx.commit();

		return tagEntity;
	}

	public void delete(Key key) {
		Transaction tx = Datastore.beginTransaction();
		TagEntity tag = Datastore.get(tx, meta, key);
		Datastore.delete(tx, tag.getKey());
		tx.commit();
	}
}
