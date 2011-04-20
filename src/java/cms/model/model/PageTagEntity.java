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

	public ModelRef<TagEntity> getTagRef() {
		return tagRef;
	}

	@Override
	public String toString() {
		StringBuilder ret = new StringBuilder("PageTagEntity{");
		ret.append("Key:").append(key).append(",");
		ret.append("Page:").append(pageRef.getModel().getUrl()).append(",");
		ret.append("Tag:").append(tagRef.getModel().getName());
		ret.append("}");

		return ret.toString();
	}
}