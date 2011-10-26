package cms.model.meta;

import cms.model.model.PageEntity;
import cms.model.model.TemplateEntity;
import com.google.appengine.api.datastore.AsyncDatastoreService;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import org.slim3.datastore.CoreAttributeMeta;
import org.slim3.datastore.ModelMeta;
import org.slim3.datastore.ModelRef;
import org.slim3.datastore.ModelRefAttributeMeta;
import org.slim3.datastore.StringAttributeMeta;
import org.slim3.datastore.json.Default;
import org.slim3.datastore.json.JsonCoder;
import org.slim3.datastore.json.JsonReader;
import org.slim3.datastore.json.JsonRootReader;
import org.slim3.datastore.json.JsonWriter;

public final class PageEntityMeta extends ModelMeta<PageEntity> {

	public final CoreAttributeMeta<PageEntity, Key> key =
			new CoreAttributeMeta<PageEntity, Key>(this, "__key__", "key", Key.class);

	public final CoreAttributeMeta<PageEntity, Long> version =
			new CoreAttributeMeta<PageEntity, Long>(this, "version", "version", Long.class);

	public final StringAttributeMeta<PageEntity> url =
			new StringAttributeMeta<PageEntity>(this, "url", "url");

	public final StringAttributeMeta<PageEntity> title =
			new StringAttributeMeta<PageEntity>(this, "title", "title");

	public final StringAttributeMeta<PageEntity> content =
			new StringAttributeMeta<PageEntity>(this, "content", "content");

	public final CoreAttributeMeta<PageEntity, Boolean> visible =
			new CoreAttributeMeta<PageEntity, Boolean>(this, "visible", "visible", Boolean.class);

//	public final CoreAttributeMeta<PageEntity, Integer> position =
//			new CoreAttributeMeta<PageEntity, Integer>(this, "position", "position", Integer.class);

	public final ModelRefAttributeMeta<PageEntity, ModelRef<TemplateEntity>, TemplateEntity> templateRef =
			new ModelRefAttributeMeta<PageEntity, ModelRef<TemplateEntity>, TemplateEntity>(this, "templateRef", "templateRef", ModelRef.class, TemplateEntity.class);

	//private static final persistencejs.utils.CreatedDate slim3_createdDateAttributeListener = new persistencejs.utils.CreatedDate();

	private static final PageEntityMeta slim3_singleton = new PageEntityMeta();

	/**
	 * @return the singleton
	 */
	public static PageEntityMeta get() {
	   return slim3_singleton;
	}

	/** */
	public PageEntityMeta() {
		super("PageEntity", PageEntity.class);
	}

	@Override
	public PageEntity entityToModel(Entity entity) {
		PageEntity model = new PageEntity();
		model.setKey(entity.getKey());
		model.setVersion((Long) entity.getProperty("version"));
		model.setUrl((String) entity.getProperty("url"));
		model.setTitle((String) entity.getProperty("title"));
		model.setContent((String) entity.getProperty("content"));
		model.setVisible(booleanToPrimitiveBoolean(((Boolean) entity.getProperty("visible"))));
//		model.setPosition(longToPrimitiveInt((Long) entity.getProperty("position")));
		if (model.getTemplateRef() == null) {
			throw new NullPointerException("The property (templateRef) is null.");
		}
		model.getTemplateRef().setKey((Key) entity.getProperty("templateRef"));
		return model;
	}

	@Override
	public Entity modelToEntity(Object model) {
		PageEntity m = (PageEntity) model;
		Entity entity = null;
		if (m.getKey() != null) {
			entity = new Entity(m.getKey());
		} else {
			entity = new Entity(kind);
		}
		entity.setProperty("version", m.getVersion());
		entity.setProperty("url", m.getUrl());
		entity.setProperty("title", m.getTitle());
		entity.setProperty("content", m.getContent());
		entity.setProperty("visible", m.getVisible());
//		entity.setProperty("position", m.getPosition());
		if (m.getTemplateRef() == null) {
			throw new NullPointerException("The property (templateRef) must not be null.");
		}
		entity.setProperty("templateRef", m.getTemplateRef().getKey());
		entity.setProperty("slim3.schemaVersion", 1);
		return entity;
	}

	@Override
	protected Key getKey(Object model) {
		PageEntity m = (PageEntity) model;
		return m.getKey();
	}

	@Override
	protected void setKey(Object model, Key key) {
		validateKey(key);
		PageEntity m = (PageEntity) model;
		m.setKey(key);
	}

	@Override
	protected long getVersion(Object model) {
		PageEntity m = (PageEntity) model;
		return m.getVersion() != null ? m.getVersion().longValue() : 0L;
	}

	@Override
	protected void incrementVersion(Object model) {
		PageEntity m = (PageEntity) model;
		long ver = m.getVersion() != null ? m.getVersion().longValue() : 0L;
		m.setVersion(Long.valueOf(ver + 1L));
	}

	@Override
	protected void assignKeyToModelRefIfNecessary(AsyncDatastoreService ds, Object model) {
		PageEntity m = (PageEntity) model;
		if (m.getTemplateRef() == null) {
			throw new NullPointerException("The property (templateRef) must not be null.");
		}
		m.getTemplateRef().assignKeyIfNecessary(ds);
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
		PageEntity m = (PageEntity) model;
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

		writer.setNextPropertyName("url");
		encoder = new Default();
		encoder.encode(writer, m.getUrl());

		writer.setNextPropertyName("title");
		encoder = new Default();
		encoder.encode(writer, m.getTitle());

		writer.setNextPropertyName("content");
		encoder = new Default();
		encoder.encode(writer, m.getContent());

		writer.setNextPropertyName("visible");
		encoder = new Default();
		encoder.encode(writer, m.getVisible());

//		writer.setNextPropertyName("position");
//		encoder = new Default();
//		encoder.encode(writer, m.getPosition());

		if(m.getTemplateRef() != null && m.getTemplateRef().getKey() != null){
			writer.setNextPropertyName("templateRef");
			encoder = new Default();
			encoder.encode(writer, m.getTemplateRef(), maxDepth, currentDepth);
		}
		writer.endObject();
	}

	@Override
	public PageEntity jsonToModel(JsonRootReader rootReader, int maxDepth, int currentDepth) {
		PageEntity m = new PageEntity();
		JsonReader reader = null;
		JsonCoder decoder = null;

		reader = rootReader.newObjectReader("key");
		decoder = new Default();
		m.setKey(decoder.decode(reader, m.getKey()));

		reader = rootReader.newObjectReader("version");
		decoder = new Default();
		m.setVersion(decoder.decode(reader, m.getVersion()));

		reader = rootReader.newObjectReader("url");
		decoder = new Default();
		m.setUrl(decoder.decode(reader, m.getUrl()));

		reader = rootReader.newObjectReader("title");
		decoder = new Default();
		m.setTitle(decoder.decode(reader, m.getTitle()));

		reader = rootReader.newObjectReader("content");
		decoder = new Default();
		m.setTitle(decoder.decode(reader, m.getContent()));

		reader = rootReader.newObjectReader("visible");
		decoder = new Default();
		m.setVisible(decoder.decode(reader, m.getVisible()));

//		reader = rootReader.newObjectReader("position");
//		decoder = new Default();
//		m.setPosition(decoder.decode(reader, m.getPosition()));

		reader = rootReader.newObjectReader("templateRef");
		decoder = new Default();
		decoder.decode(reader, m.getTemplateRef(), maxDepth, currentDepth);

		return m;
	}
}
