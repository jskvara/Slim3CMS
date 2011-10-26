package cms.model.meta;

import cms.model.model.NewsEntity;
import com.google.appengine.api.datastore.AsyncDatastoreService;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import java.util.Date;
import org.slim3.datastore.CoreAttributeMeta;
import org.slim3.datastore.ModelMeta;
import org.slim3.datastore.StringAttributeMeta;
import org.slim3.datastore.json.Default;
import org.slim3.datastore.json.JsonCoder;
import org.slim3.datastore.json.JsonReader;
import org.slim3.datastore.json.JsonRootReader;
import org.slim3.datastore.json.JsonWriter;

public final class NewsEntityMeta extends ModelMeta<NewsEntity> {

	public final CoreAttributeMeta<NewsEntity, Key> key =
			new CoreAttributeMeta<NewsEntity, Key>(this, "__key__", "key", Key.class);

	public final CoreAttributeMeta<NewsEntity, Long> version =
			new CoreAttributeMeta<NewsEntity, Long>(this, "version", "version", Long.class);

	public final StringAttributeMeta<NewsEntity> title =
			new StringAttributeMeta<NewsEntity>(this, "title", "title");

	public final StringAttributeMeta<NewsEntity> text =
			new StringAttributeMeta<NewsEntity>(this, "text", "text");

	public final CoreAttributeMeta<NewsEntity, Date> created =
			new CoreAttributeMeta<NewsEntity, Date>(this, "created", "created", Date.class);

	public final CoreAttributeMeta<NewsEntity, Boolean> visible =
			new CoreAttributeMeta<NewsEntity, Boolean>(this, "visible", "visible", Boolean.class);

	private static final NewsEntityMeta slim3_singleton = new NewsEntityMeta();

	public static NewsEntityMeta get() {
		return slim3_singleton;
	}

	public NewsEntityMeta() {
		super("NewsEntity", NewsEntity.class);
	}

	@Override
	public NewsEntity entityToModel(Entity entity) {
		NewsEntity model = new NewsEntity();
		model.setKey(entity.getKey());
		model.setVersion((Long) entity.getProperty("version"));
		model.setTitle((String) entity.getProperty("title"));
		model.setText((String) entity.getProperty("text"));
		model.setCreated((Date) entity.getProperty("created"));
		model.setVisible(booleanToPrimitiveBoolean(((Boolean) entity.getProperty("visible"))));
		return model;
	}

	@Override
	public Entity modelToEntity(Object model) {
		NewsEntity m = (NewsEntity) model;
		Entity entity = null;
		if (m.getKey() != null) {
			entity = new Entity(m.getKey());
		} else {
			entity = new Entity(kind);
		}
		entity.setProperty("version", m.getVersion());
		entity.setProperty("title", m.getTitle());
		entity.setProperty("text", m.getText());
		entity.setProperty("created", m.getCreated());
		entity.setProperty("visible", m.getVisible());
		entity.setProperty("slim3.schemaVersion", 1);
		return entity;
	}

	@Override
	protected Key getKey(Object model) {
		NewsEntity m = (NewsEntity) model;
		return m.getKey();
	}

	@Override
	protected void setKey(Object model, Key key) {
		validateKey(key);
		NewsEntity m = (NewsEntity) model;
		m.setKey(key);
	}

	@Override
	protected long getVersion(Object model) {
		NewsEntity m = (NewsEntity) model;
		return m.getVersion() != null ? m.getVersion().longValue() : 0L;
	}

	@Override
	protected void incrementVersion(Object model) {
		NewsEntity m = (NewsEntity) model;
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
		NewsEntity m = (NewsEntity) model;
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

		writer.setNextPropertyName("tile");
		encoder = new Default();
		encoder.encode(writer, m.getTitle());

		writer.setNextPropertyName("text");
		encoder = new Default();
		encoder.encode(writer, m.getText());

		writer.setNextPropertyName("created");
		encoder = new Default();
		encoder.encode(writer, m.getCreated());

		writer.setNextPropertyName("visible");
		encoder = new Default();
		encoder.encode(writer, m.getVisible());
		writer.endObject();
	}

	@Override
	protected NewsEntity jsonToModel(JsonRootReader rootReader, int maxDepth, int currentDepth) {
		NewsEntity m = new NewsEntity();
		JsonReader reader = null;
		JsonCoder decoder = null;

		reader = rootReader.newObjectReader("key");
		decoder = new Default();
		m.setKey(decoder.decode(reader, m.getKey()));

		reader = rootReader.newObjectReader("version");
		decoder = new Default();
		m.setVersion(decoder.decode(reader, m.getVersion()));

		reader = rootReader.newObjectReader("title");
		decoder = new Default();
		m.setTitle(decoder.decode(reader, m.getTitle()));

		reader = rootReader.newObjectReader("text");
		decoder = new Default();
		m.setText(decoder.decode(reader, m.getText()));

		reader = rootReader.newObjectReader("created");
		decoder = new Default();
		m.setCreated(decoder.decode(reader, m.getCreated()));

		reader = rootReader.newObjectReader("visible");
		decoder = new Default();
		m.setVisible(decoder.decode(reader, m.getVisible()));
		return m;
	}
}
