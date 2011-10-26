package cms.model.meta;

import cms.model.model.TemplateEntity;
import com.google.appengine.api.datastore.AsyncDatastoreService;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Text;
import org.slim3.datastore.CoreAttributeMeta;
import org.slim3.datastore.ModelMeta;
import org.slim3.datastore.StringAttributeMeta;
import org.slim3.datastore.json.Default;
import org.slim3.datastore.json.JsonCoder;
import org.slim3.datastore.json.JsonReader;
import org.slim3.datastore.json.JsonRootReader;
import org.slim3.datastore.json.JsonWriter;

public final class TemplateEntityMeta extends ModelMeta<TemplateEntity> {

	public final CoreAttributeMeta<TemplateEntity, Key> key =
			new CoreAttributeMeta<TemplateEntity, Key>(this, "__key__", "key", Key.class);
	public final CoreAttributeMeta<TemplateEntity, Long> version =
			new CoreAttributeMeta<TemplateEntity, Long>(this, "version", "version", Long.class);
	public final StringAttributeMeta<TemplateEntity> name =
			new StringAttributeMeta<TemplateEntity>(this, "name", "name");
	public final CoreAttributeMeta<TemplateEntity, Text> content =
			new CoreAttributeMeta<TemplateEntity, Text>(this, "content", "content", Text.class);
	private static final TemplateEntityMeta slim3_singleton = new TemplateEntityMeta();

	/**
	 * @return the singleton
	 */
	public static TemplateEntityMeta get() {
		return slim3_singleton;
	}

	public TemplateEntityMeta() {
		super("TemplateEntity", TemplateEntity.class);
	}

	@Override
	public TemplateEntity entityToModel(Entity entity) {
		TemplateEntity model = new TemplateEntity();
		model.setKey(entity.getKey());
		model.setVersion((Long) entity.getProperty("version"));
		model.setName((String) entity.getProperty("name"));
		model.setContent((Text) entity.getProperty("content"));
		return model;
	}

	@Override
	public Entity modelToEntity(Object model) {
		TemplateEntity m = (TemplateEntity) model;
		Entity entity = null;
		if (m.getKey() != null) {
			entity = new Entity(m.getKey());
		} else {
			entity = new Entity(kind);
		}
		entity.setProperty("version", m.getVersion());
		entity.setProperty("name", m.getName());
		entity.setProperty("content", m.getContent());
		return entity;
	}

	@Override
	protected Key getKey(Object model) {
		TemplateEntity m = (TemplateEntity) model;
		return m.getKey();
	}

	@Override
	protected void setKey(Object model, Key key) {
		validateKey(key);
		TemplateEntity m = (TemplateEntity) model;
		m.setKey(key);
	}

	@Override
	protected long getVersion(Object model) {
		TemplateEntity m = (TemplateEntity) model;
		return m.getVersion() != null ? m.getVersion().longValue() : 0L;
	}

	@Override
	protected void incrementVersion(Object model) {
		TemplateEntity m = (TemplateEntity) model;
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
		TemplateEntity m = (TemplateEntity) model;
		writer.beginObject();
		JsonCoder encoder = null;

		if (m.getKey() != null) {
			writer.setNextPropertyName("key");
			encoder = new Default();
			encoder.encode(writer, m.getKey());
		}

		if(m.getVersion() != null){
			writer.setNextPropertyName("version");
			encoder = new Default();
			encoder.encode(writer, m.getVersion());
		}

		writer.setNextPropertyName("name");
		encoder = new Default();
		encoder.encode(writer, m.getName());

		if (m.getContent() != null) {
			writer.setNextPropertyName("content");
			encoder = new Default();
			encoder.encode(writer, m.getContent().getValue());
		}
		writer.endObject();
	}

	@Override
	protected TemplateEntity jsonToModel(JsonRootReader rootReader, int maxDepth, int currentDepth) {
		TemplateEntity m = new TemplateEntity();
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

		reader = rootReader.newObjectReader("content");
		decoder = new Default();
		m.setContent(decoder.decode(reader, m.getContent()));
		return m;
	}
}
