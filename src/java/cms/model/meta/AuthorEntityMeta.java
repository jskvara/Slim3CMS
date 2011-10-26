package cms.model.meta;

import cms.model.model.AuthorEntity;
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

public final class AuthorEntityMeta extends ModelMeta<AuthorEntity> {

	public final CoreAttributeMeta<AuthorEntity, Key> key =
			new CoreAttributeMeta<AuthorEntity, Key>(this, "__key__", "key", Key.class);

	public final CoreAttributeMeta<AuthorEntity, Long> version =
			new CoreAttributeMeta<AuthorEntity, Long>(this, "version", "version", Long.class);

	public final StringAttributeMeta<AuthorEntity> email =
			new StringAttributeMeta<AuthorEntity>(this, "email", "email");

	private static final AuthorEntityMeta slim3_singleton = new AuthorEntityMeta();

	/**
	 * @return the singleton
	 */
	public static AuthorEntityMeta get() {
	   return slim3_singleton;
	}

	/** */
	public AuthorEntityMeta() {
		super("AuthorEntity", AuthorEntity.class);
	}

	@Override
	public AuthorEntity entityToModel(Entity entity) {
		AuthorEntity model = new AuthorEntity();
		model.setKey(entity.getKey());
		model.setVersion((Long) entity.getProperty("version"));
		model.setEmail((String) entity.getProperty("email"));
		return model;
	}

	@Override
	public Entity modelToEntity(Object model) {
		AuthorEntity m = (AuthorEntity) model;
		Entity entity = null;
		if (m.getKey() != null) {
			entity = new Entity(m.getKey());
		} else {
			entity = new Entity(kind);
		}
		entity.setProperty("version", m.getVersion());
		entity.setProperty("email", m.getEmail());
		return entity;
	}

	@Override
	protected Key getKey(Object model) {
		AuthorEntity m = (AuthorEntity) model;
		return m.getKey();
	}

	@Override
	protected void setKey(Object model, Key key) {
		validateKey(key);
		AuthorEntity m = (AuthorEntity) model;
		m.setKey(key);
	}

	@Override
	protected long getVersion(Object model) {
		AuthorEntity m = (AuthorEntity) model;
		return m.getVersion() != null ? m.getVersion().longValue() : 0L;
	}

	@Override
	protected void incrementVersion(Object model) {
		AuthorEntity m = (AuthorEntity) model;
		long ver = m.getVersion() != null ? m.getVersion().longValue() : 0L;
		m.setVersion(Long.valueOf(ver + 1L));
	}

	@Override
	protected void assignKeyToModelRefIfNecessary(AsyncDatastoreService ds, Object model) {
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
	protected boolean isCipherProperty(String propertyName) {
		return false;
	}

	@Override
	protected void modelToJson(JsonWriter writer, Object model, int maxDepth, int currentDepth) {
		AuthorEntity m = (AuthorEntity) model;
		writer.beginObject();
		JsonCoder encoder = null;

		if(m.getKey() != null){
			writer.setNextPropertyName("key");
			encoder = new Default();
			encoder.encode(writer, m.getKey());
		}

		if(m.getVersion() != null){
			writer.setNextPropertyName("version");
			encoder = new Default();
			encoder.encode(writer, m.getVersion());
		}

		writer.setNextPropertyName("email");
		encoder = new Default();
		encoder.encode(writer, m.getEmail());

		writer.endObject();
	}

	@Override
	public AuthorEntity jsonToModel(JsonRootReader rootReader, int maxDepth, int currentDepth) {
		AuthorEntity m = new AuthorEntity();
		JsonReader reader = null;
		JsonCoder decoder = null;

		reader = rootReader.newObjectReader("key");
		decoder = new Default();
		m.setKey(decoder.decode(reader, m.getKey()));

		reader = rootReader.newObjectReader("version");
		decoder = new Default();
		m.setVersion(decoder.decode(reader, m.getVersion()));

		reader = rootReader.newObjectReader("email");
		decoder = new Default();
		m.setEmail(decoder.decode(reader, m.getEmail()));

		return m;
	}
}
