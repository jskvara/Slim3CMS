package cms.model.meta;

import cms.model.model.PageEntity;
import cms.model.model.PageTagEntity;
import cms.model.model.TagEntity;
import com.google.appengine.api.datastore.AsyncDatastoreService;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import org.slim3.datastore.CoreAttributeMeta;
import org.slim3.datastore.ModelMeta;
import org.slim3.datastore.ModelRef;
import org.slim3.datastore.ModelRefAttributeMeta;
import org.slim3.datastore.json.Default;
import org.slim3.datastore.json.JsonCoder;
import org.slim3.datastore.json.JsonReader;
import org.slim3.datastore.json.JsonRootReader;
import org.slim3.datastore.json.JsonWriter;

public final class PageTagEntityMeta extends ModelMeta<PageTagEntity> {

	public final CoreAttributeMeta<PageTagEntity, Key> key =
			new CoreAttributeMeta<PageTagEntity, Key>(this, "__key__", "key", Key.class);

	public final ModelRefAttributeMeta<PageTagEntity, ModelRef<PageEntity>, PageEntity> pageRef =
			new ModelRefAttributeMeta<PageTagEntity, ModelRef<PageEntity>, PageEntity>(this, "pageRef", "pageRef", ModelRef.class, PageEntity.class);

	public final ModelRefAttributeMeta<PageTagEntity, ModelRef<TagEntity>, TagEntity> tagRef =
			new ModelRefAttributeMeta<PageTagEntity, ModelRef<TagEntity>, TagEntity>(this, "tagRef", "tagRef", ModelRef.class, TagEntity.class);

	private static final PageTagEntityMeta slim3_singleton = new PageTagEntityMeta();

	/**
	 * @return the singleton
	 */
	public static PageTagEntityMeta get() {
	   return slim3_singleton;
	}

	/** */
	public PageTagEntityMeta() {
		super("PageTagEntity", PageTagEntity.class);
	}

	@Override
	public PageTagEntity entityToModel(Entity entity) {
		PageTagEntity model = new PageTagEntity();
		model.setKey(entity.getKey());
		if (model.getPageRef() == null) {
			throw new NullPointerException("The property (pageRef) is null.");
		}
		model.getPageRef().setKey((Key) entity.getProperty("pageRef"));
		if (model.getTagRef() == null) {
			throw new NullPointerException("The property (tagRef) is null.");
		}
		model.getTagRef().setKey((Key) entity.getProperty("tagRef"));
		return model;
	}

	@Override
	public Entity modelToEntity(Object model) {
		PageTagEntity m = (PageTagEntity) model;
		Entity entity = null;
		if (m.getKey() != null) {
			entity = new Entity(m.getKey());
		} else {
			entity = new Entity(kind);
		}
		if (m.getPageRef() == null) {
			throw new NullPointerException("The property (pageRef) must not be null.");
		}
		entity.setProperty("pageRef", m.getPageRef().getKey());
		if (m.getTagRef() == null) {
			throw new NullPointerException("The property (tagRef) must not be null.");
		}
		entity.setProperty("tagRef", m.getTagRef().getKey());
		return entity;
	}

	@Override
	protected Key getKey(Object model) {
		PageTagEntity m = (PageTagEntity) model;
		return m.getKey();
	}

	@Override
	protected void setKey(Object model, Key key) {
		validateKey(key);
		PageTagEntity m = (PageTagEntity) model;
		m.setKey(key);
	}

	@Override
	protected long getVersion(Object model) {
		return 0L;
	}

	@Override
	protected void incrementVersion(Object model) {
	}

	@Override
	protected void assignKeyToModelRefIfNecessary(AsyncDatastoreService ds, Object model) {
		PageTagEntity m = (PageTagEntity) model;
		if (m.getPageRef() == null) {
			throw new NullPointerException("The property (pageRef) must not be null.");
		}
		m.getPageRef().assignKeyIfNecessary(ds);

		if (m.getTagRef() == null) {
			throw new NullPointerException("The property (tagRef) must not be null.");
		}
		m.getTagRef().assignKeyIfNecessary(ds);
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
		PageTagEntity m = (PageTagEntity) model;
		writer.beginObject();
		JsonCoder encoder = null;
		if(m.getKey() != null){
			writer.setNextPropertyName("key");
			encoder = new Default();
			encoder.encode(writer, m.getKey());
		}

		if(m.getPageRef() != null && m.getPageRef().getKey() != null){
			writer.setNextPropertyName("pageRef");
			encoder = new Default();
			encoder.encode(writer, m.getPageRef(), maxDepth, currentDepth);
		}

		if(m.getTagRef() != null && m.getTagRef().getKey() != null){
			writer.setNextPropertyName("tagRef");
			encoder = new Default();
			encoder.encode(writer, m.getTagRef(), maxDepth, currentDepth);
		}
		writer.endObject();
	}

	@Override
	public PageTagEntity jsonToModel(JsonRootReader rootReader, int maxDepth, int currentDepth) {
		PageTagEntity m = new PageTagEntity();
		JsonReader reader = null;
		JsonCoder decoder = null;
		reader = rootReader.newObjectReader("key");
		decoder = new Default();
		m.setKey(decoder.decode(reader, m.getKey()));

		reader = rootReader.newObjectReader("pageRef");
		decoder = new Default();
		decoder.decode(reader, m.getPageRef(), maxDepth, currentDepth);

		reader = rootReader.newObjectReader("tagRef");
		decoder = new Default();
		decoder.decode(reader, m.getTagRef(), maxDepth, currentDepth);

		return m;
	}
}
