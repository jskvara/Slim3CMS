package cms.model.dao;

import cms.model.model.NewsEntity;
import cms.model.meta.NewsMeta;
import com.google.appengine.api.datastore.Key;
import java.util.ArrayList;
import java.util.List;
import org.slim3.datastore.Datastore;

public class NewsDAO implements IDAO {
	private NewsMeta newsMeta = NewsMeta.get();
	
	public List<NewsEntity> getAll() {
		// List<NewsEntity> newsEntities = Datastore.query(newsMeta).sort(newsMeta.created.desc).asList();
		List<NewsEntity> newsEntities = new ArrayList<NewsEntity>();
		return newsEntities;
	}

	public NewsEntity get(Key key) {
		NewsEntity newsEntity = Datastore.get(NewsEntity.class, key);
		return newsEntity;
	}

	public void insert(NewsEntity newsEntity) {
		Datastore.put(newsEntity);
	}

	public void edit(NewsEntity newsEntity) {
		Datastore.put(newsEntity);
	}

	public void delete(Key key) {
		Datastore.delete(key);
	}
}
