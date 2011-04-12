package cms.model.dao;

import cms.model.model.NewsEntity;
import cms.model.meta.NewsEntityMeta;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Transaction;
import java.util.List;
import org.slim3.datastore.Datastore;

public class NewsDAO implements IDAO {
	private NewsEntityMeta newsMeta = NewsEntityMeta.get();
	
	public List<NewsEntity> getAll() {
		List<NewsEntity> newsEntities = Datastore.query(newsMeta).sort(newsMeta.created.desc).asList();
		return newsEntities;
	}

	public NewsEntity get(Key key) {
		NewsEntity newsEntity = Datastore.get(NewsEntity.class, key);
		return newsEntity;
	}

	public NewsEntity insert(NewsEntity newsEntity) {
		Transaction tx = Datastore.beginTransaction();
		Datastore.put(newsEntity);
		tx.commit();

		return newsEntity;
	}

	public NewsEntity edit(NewsEntity newsEntity) {
		Transaction tx = Datastore.beginTransaction();
		Datastore.put(newsEntity);
		tx.commit();

		return newsEntity;
	}

	public void delete(Key key) {
		Transaction tx = Datastore.beginTransaction();
		NewsEntity news = Datastore.get(tx, newsMeta, key);
		Datastore.delete(tx, news.getKey());
		tx.commit();
	}
}
