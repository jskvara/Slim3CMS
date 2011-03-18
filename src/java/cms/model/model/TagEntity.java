package cms.model.model;

import com.google.appengine.api.datastore.Key;
import org.slim3.datastore.Attribute;
import org.slim3.datastore.InverseModelListRef;
import org.slim3.datastore.Model;

@Model(schemaVersion = 1)
public class TagEntity implements IEntity {
	private static final long serialVersionUID = 1L;

	@Attribute(primaryKey = true)
	private Key key;

	@Attribute(version = true)
	private Long version;

	@Attribute(persistent = false)
	private InverseModelListRef<PageTagEntity, TagEntity> pageTagListRef = new
			InverseModelListRef<PageTagEntity, TagEntity>
			(PageTagEntity.class, "tagRef", this);

	private String name;

	public Key getKey() {
		return key;
	}

	public void setKey(Key key) {
		this.key = key;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public InverseModelListRef<PageTagEntity, TagEntity> getPageTagListRef() {
		return pageTagListRef;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}