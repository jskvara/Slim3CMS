package cms.model.dao;

import cms.model.model.NewsEntity;
import cms.model.meta.NewsEntityMeta;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Transaction;
import java.util.ConcurrentModificationException;
import java.util.Date;
import java.util.List;
import org.slim3.datastore.Datastore;
import org.slim3.datastore.EntityNotFoundRuntimeException;

public class NewsDAO implements DAO {
	private NewsEntityMeta meta = NewsEntityMeta.get();

	public List<NewsEntity> getAll() {
		List<NewsEntity> newsEntities = Datastore.query(meta)
				.sort(meta.created.desc).asList();
		return newsEntities;
	}

	public List<NewsEntity> getAllVisible() {
		List<NewsEntity> newsEntities = Datastore.query(meta)
				.filter(meta.visible.equal(Boolean.TRUE))
				.filter(meta.created.lessThanOrEqual(new Date()))
				.sort(meta.created.desc)
				.asList();
		return newsEntities;
	}

	public List<NewsEntity> getHomepage() {
		List<NewsEntity> newsEntities = Datastore.query(meta)
				.filter(meta.visible.equal(Boolean.TRUE))
				.filter(meta.created.lessThanOrEqual(new Date()))
				.sort(meta.created.desc)
				.limit(10)
				.asList();
		return newsEntities;
	}

	public NewsEntity get(Key key) {
		NewsEntity newsEntity = null;
		try {
			newsEntity = Datastore.get(NewsEntity.class, key);
		} catch (EntityNotFoundRuntimeException e) {
			return null;
		}

		return newsEntity;
	}

	public NewsEntity insert(NewsEntity newsEntity) {
		Transaction tx = Datastore.beginTransaction();
		Datastore.put(newsEntity);
		tx.commit();

		return newsEntity;
	}

	public NewsEntity edit(NewsEntity newsEntity) throws ConcurrentModificationException {
		Transaction tx = Datastore.beginTransaction();
		try {
			Datastore.get(tx, NewsEntity.class, newsEntity.getKey(), newsEntity.getVersion());

			Datastore.put(tx, newsEntity);
			tx.commit();
		} catch (ConcurrentModificationException e) {
			if (tx.isActive()) {
				tx.rollback();
			}
			throw e;
		}

		return newsEntity;
	}

	public void delete(Key key, Long version) throws ConcurrentModificationException {
		Transaction tx = Datastore.beginTransaction();
		try {
			NewsEntity newsEntity = Datastore.get(tx, meta, key, version);

			Datastore.delete(tx, newsEntity.getKey());
			tx.commit();
		} catch (ConcurrentModificationException e) {
			if (tx.isActive()) {
				tx.rollback();
			}
			throw e;
		}
	}
}
