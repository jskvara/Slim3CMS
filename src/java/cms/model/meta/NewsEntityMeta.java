package cms.model.meta;

import cms.model.model.NewsEntity;
import com.google.appengine.api.datastore.AsyncDatastoreService;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import org.slim3.datastore.ModelMeta;
import org.slim3.datastore.json.JsonRootReader;
import org.slim3.datastore.json.JsonWriter;

public class NewsEntityMeta extends ModelMeta<NewsEntity> {
	public static NewsEntityMeta get() {
		return new NewsEntityMeta();
	}

	@Override
	public String getSchemaVersionName() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public String getClassHierarchyListName() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public NewsEntity entityToModel(Entity entity) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public Entity modelToEntity(Object model) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	protected void modelToJson(JsonWriter writer, Object model, int maxDepth, int currentDepth) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	protected NewsEntity jsonToModel(JsonRootReader reader, int maxDepth, int currentDepth) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	protected long getVersion(Object model) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	protected void incrementVersion(Object model) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	protected void prePut(Object model) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	protected Key getKey(Object model) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	protected void setKey(Object model, Key key) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	protected void assignKeyToModelRefIfNecessary(AsyncDatastoreService ds, Object model) throws NullPointerException {
		throw new UnsupportedOperationException("Not supported yet.");
	}
}
