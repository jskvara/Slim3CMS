package cms.model.dao;

import cms.model.model.PageEntity;
import cms.model.meta.PageEntityMeta;
import cms.model.model.PageTagEntity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Transaction;
import java.util.ConcurrentModificationException;
import java.util.List;
import org.slim3.datastore.Datastore;
import org.slim3.datastore.EntityNotFoundRuntimeException;

public class PageDAO implements DAO {

	private PageEntityMeta meta = PageEntityMeta.get();

	public List<PageEntity> getAll() {
		List<PageEntity> pageEntities = Datastore.query(meta)
				//.sort(pageMeta.position.desc)
				.asList();

		return pageEntities;
	}

	public List<PageEntity> getVisible() {
		List<PageEntity> pageEntities = Datastore.query(meta)
				.filter(meta.visible.equal(true))
				//.sort(pageMeta.position.desc)
				.asList();

		return pageEntities;
	}

	public PageEntity get(Key key) {
		PageEntity pageEntity = null;
		try {
			pageEntity = Datastore.get(PageEntity.class, key);
		} catch (EntityNotFoundRuntimeException e) {
			return null;
		}

		return pageEntity;
	}

	public PageEntity getByUrl(String url) {
		PageEntity pageEntity = Datastore.query(meta)
				.filter(meta.url.equal(url)).asSingle();

		return pageEntity;
	}

	public PageEntity getVisibleByUrl(String url) {
		PageEntity pageEntity = Datastore.query(meta)
				.filter(meta.url.equal(url))
				.filter(meta.visible.equal(true)).asSingle();

		return pageEntity;
	}

	public PageEntity insert(PageEntity pageEntity) {
		Transaction tx = Datastore.beginTransaction();
		Datastore.put(tx, pageEntity);
		tx.commit();

		return pageEntity;
	}

	public PageEntity edit(PageEntity pageEntity) throws ConcurrentModificationException {
		Transaction tx = Datastore.beginTransaction();
		try {
			Datastore.get(tx, PageEntity.class, pageEntity.getKey(), pageEntity.getVersion());

			Datastore.put(tx, pageEntity);
			tx.commit();
		} catch (ConcurrentModificationException e) {
			if (tx.isActive()) {
				tx.rollback();
			}
			throw e;
		}

		return pageEntity;
	}

	public void delete(Key key, Long version) throws ConcurrentModificationException {
		Transaction tx = Datastore.beginTransaction();
		try {
			PageEntity pageEntity = Datastore.get(tx, meta, key, version);
			for (PageTagEntity pageTagEntity : pageEntity.getPageTagListRef().getModelList()) {
				Datastore.deleteWithoutTx(pageTagEntity.getKey());
			}
			Datastore.delete(tx, pageEntity.getKey());
			tx.commit();
		} catch (ConcurrentModificationException e) {
			if (tx.isActive()) {
				tx.rollback();
			}
			throw e;
		}
	}
}
