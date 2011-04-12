package cms.model.dao;

import cms.model.model.TagEntity;
import cms.model.meta.TagEntityMeta;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Transaction;
import java.util.List;
import org.slim3.datastore.Datastore;

public class TagDAO implements IDAO {
	private TagEntityMeta tagMeta = TagEntityMeta.get();
	
	public List<TagEntity> getAll() {
		List<TagEntity> tagEntities = Datastore.query(tagMeta).sort(tagMeta.name.desc).asList();
		return tagEntities;
	}

	public TagEntity get(Key key) {
		TagEntity tagEntity = Datastore.get(TagEntity.class, key);
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
		TagEntity tag = Datastore.get(tx, tagMeta, key);
		Datastore.delete(tx, tag.getKey());
		tx.commit();
	}
}
