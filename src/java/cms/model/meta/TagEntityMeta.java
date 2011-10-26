package cms.model.meta;

import cms.model.model.TagEntity;
import com.google.appengine.api.datastore.AsyncDatastoreService;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import org.slim3.datastore.CoreAttributeMeta;
import org.slim3.datastore.ModelMeta;
import org.slim3.datastore.StringAttributeMeta;
import org.slim3.datastore.json.Default;
import org.slim3.datastore.json.JsonCoder;
import org.slim3.datastore.json.JsonReader;
import org.slim3.datastore.json.JsonRootReader;
import org.slim3.datastore.json.JsonWriter;

public class TagEntityMeta extends ModelMeta<TagEntity> {

	public final CoreAttributeMeta<TagEntity, Key> key =
			new CoreAttributeMeta<TagEntity, Key>(this, "__key__", "key", Key.class);
	public final CoreAttributeMeta<TagEntity, Long> version =
			new CoreAttributeMeta<TagEntity, Long>(this, "version", "version", Long.class);
	public final StringAttributeMeta<TagEntity> name =
			new StringAttributeMeta<TagEntity>(this, "name", "name");
	private static final TagEntityMeta slim3_singleton = new TagEntityMeta();

	/**
	 * @return the singleton
	 */
	public static TagEntityMeta get() {
		return slim3_singleton;
	}

	/** */
	public TagEntityMeta() {
		super("TagEntity", TagEntity.class);
	}

	@Override
	public TagEntity entityToModel(Entity entity) {
		TagEntity model = new TagEntity();
		model.setKey(entity.getKey());
		model.setVersion((Long) entity.getProperty("version"));
		model.setName((String) entity.getProperty("name"));
		return model;
	}

	@Override
	public Entity modelToEntity(Object model) {
		TagEntity m = (TagEntity) model;
		Entity entity = null;
		if (m.getKey() != null) {
			entity = new Entity(m.getKey());
		} else {
			entity = new Entity(kind);
		}
		entity.setProperty("version", m.getVersion());
		entity.setProperty("name", m.getName());
		entity.setProperty("slim3.schemaVersion", 1);
		return entity;
	}

	@Override
	protected Key getKey(Object model) {
		TagEntity m = (TagEntity) model;
		return m.getKey();
	}

	@Override
	protected void setKey(Object model, Key key) {
		validateKey(key);
		TagEntity m = (TagEntity) model;
		m.setKey(key);
	}

	@Override
	protected long getVersion(Object model) {
		TagEntity m = (TagEntity) model;
		return m.getVersion() != null ? m.getVersion().longValue() : 0L;
	}

	@Override
	protected void incrementVersion(Object model) {
		TagEntity m = (TagEntity) model;
		long ver = m.getVersion() != null ? m.getVersion().longValue() : 0L;
		m.setVersion(Long.valueOf(ver + 1L));
	}

	@Override
	protected void assignKeyToModelRefIfNecessary(AsyncDatastoreService ds, Object model) throws NullPointerException {
	}

	@Override
	protected void prePut(Object model) {
	}

	@Override
	protected void postGet(Object model) {
	}

	@Override
	public String getSchemaVersionName() {
		return "slim3.schemaVersion";
	}

	@Override
	public String getClassHierarchyListName() {
		return "slim3.classHierarchyList";
	}

	@Override
	protected void modelToJson(JsonWriter writer, Object model, int maxDepth, int currentDepth) {
		TagEntity m = (TagEntity) model;
		writer.beginObject();
		JsonCoder encoder = null;

		if (m.getKey() != null) {
			writer.setNextPropertyName("key");
			encoder = new Default();
			encoder.encode(writer, m.getKey());
		}

		if (m.getVersion() != null) {
			writer.setNextPropertyName("version");
			encoder = new Default();
			encoder.encode(writer, m.getVersion());
		}

		writer.setNextPropertyName("name");
		encoder = new Default();
		encoder.encode(writer, m.getName());
		writer.endObject();
	}

	@Override
	protected TagEntity jsonToModel(JsonRootReader rootReader, int maxDepth, int currentDepth) {
		TagEntity m = new TagEntity();
		JsonReader reader = null;
		JsonCoder decoder = null;
		reader = rootReader.newObjectReader("key");
		decoder = new Default();
		m.setKey(decoder.decode(reader, m.getKey()));

		reader = rootReader.newObjectReader("version");
		decoder = new Default();
		m.setVersion(decoder.decode(reader, m.getVersion()));

		reader = rootReader.newObjectReader("name");
		decoder = new Default();
		m.setName(decoder.decode(reader, m.getName()));

		return m;
	}
}
