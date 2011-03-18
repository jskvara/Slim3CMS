package cms.model.dao;

import cms.model.model.PageEntity;
import cms.model.model.TagEntity;
import cms.model.meta.TagMeta;
import com.google.appengine.api.datastore.Key;
import java.util.ArrayList;
import java.util.List;
import org.slim3.datastore.Datastore;

public class TagDAO implements IDAO {
	private TagMeta tagMeta = TagMeta.get();
	
	public List<TagEntity> getAll() {
		// List<TageEntity> tagEntities = Datastore.query(tagMeta).sort(pageMeta.name.desc).asList();
		List<TagEntity> tagEntities = new ArrayList<TagEntity>();
		return tagEntities;
	}

	public TagEntity get(Key key) {
		TagEntity tagEntity = Datastore.get(TagEntity.class, key);
		return tagEntity;
	}

	public void insert(TagEntity tagEntity) {
		Datastore.put(tagEntity);
	}

	public void edit(TagEntity tagEntity) {
		Datastore.put(tagEntity);
	}

	public void delete(Key key) {
		Datastore.delete(key);
	}
}
