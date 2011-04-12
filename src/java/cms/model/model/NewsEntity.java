package cms.model.model;

import com.google.appengine.api.datastore.Key;
import java.util.Date;
import org.slim3.datastore.Attribute;
import org.slim3.datastore.CreationDate;
import org.slim3.datastore.Model;
import org.slim3.datastore.ModelRef;

@Model(schemaVersion = 1)
public class NewsEntity implements IEntity {
	private static final long serialVersionUID = 1L;

	@Attribute(primaryKey = true)
	private Key key;

//	@Attribute(version = true)
//	private Long version;

//	private ModelRef<AuthorEntity> authortRef =
//			new ModelRef<AuthorEntity>(AuthorEntity.class);

	private String title;
	
	private String text;

	@Attribute(listener = CreationDate.class)
	private Date created; // = new Date();

	private Boolean visible;

	public Key getKey() {
		return key;
	}

	public void setKey(Key key) {
		this.key = key;
	}

//	public Long getVersion() {
//		return version;
//	}
//
//	public void setVersion(Long version) {
//		this.version = version;
//	}

//	public ModelRef<AuthorEntity> getAuthortRef() {
//		return authortRef;
//	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Boolean getVisible() {
		return visible;
	}

	public void setVisible(Boolean visible) {
		this.visible = visible;
	}
}