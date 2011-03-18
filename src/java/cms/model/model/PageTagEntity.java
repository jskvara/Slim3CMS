package cms.model.model;

import com.google.appengine.api.datastore.Key;
import org.slim3.datastore.Attribute;
import org.slim3.datastore.Model;
import org.slim3.datastore.ModelRef;

@Model(schemaVersion = 1)
public class PageTagEntity implements IEntity {
	private static final long serialVersionUID = 1L;

	@Attribute(primaryKey = true)
	private Key key;

	private ModelRef<PageEntity> pageRef = new ModelRef<PageEntity>
			(PageEntity.class);
	private ModelRef<TagEntity> tagRef = new ModelRef<TagEntity>
			(TagEntity.class);

	public Key getKey() {
		return key;
	}

	public void setKey(Key key) {
		this.key = key;
	}

	public ModelRef<PageEntity> getPageRef() {
		return pageRef;
	}

	public void setPageRef(ModelRef<PageEntity> pageRef) {
		this.pageRef = pageRef;
	}

	public ModelRef<TagEntity> getTagRef() {
		return tagRef;
	}

	public void setTagRef(ModelRef<TagEntity> tagRef) {
		this.tagRef = tagRef;
	}
}