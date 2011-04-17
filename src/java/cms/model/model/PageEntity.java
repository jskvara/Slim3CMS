package cms.model.model;

import com.google.appengine.api.datastore.Key;
import org.slim3.datastore.Attribute;
import org.slim3.datastore.InverseModelListRef;
import org.slim3.datastore.Model;
import org.slim3.datastore.ModelRef;

@Model(schemaVersion = 1)
public class PageEntity implements IEntity {
	private static final long serialVersionUID = 1L;

	@Attribute(primaryKey = true)
	private Key key;

//	@Attribute(version = true)
//	private Long version;

	@Attribute(persistent = false)
	private InverseModelListRef<PageTagEntity, PageEntity> pageTagListRef =
			new InverseModelListRef<PageTagEntity, PageEntity>
			(PageTagEntity.class, "pageRef", this);

	private ModelRef<TemplateEntity> templateRef =
			new ModelRef<TemplateEntity>(TemplateEntity.class);

	private ModelRef<AuthorEntity> authorRef =
			new ModelRef<AuthorEntity>(AuthorEntity.class);
	
	private String url;
	private String title;
	private String content;
	private Boolean visible;
	private Integer position;

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

	public InverseModelListRef<PageTagEntity, PageEntity> getPageTagListRef() {
		return pageTagListRef;
	}

	public ModelRef<TemplateEntity> getTemplateRef() {
		return templateRef;
	}

	public ModelRef<AuthorEntity> getAuthorRef() {
		return authorRef;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Boolean getVisible() {
		return visible;
	}

	public void setVisible(Boolean visible) {
		this.visible = visible;
	}

	public Integer getPosition() {
		return position;
	}

	public void setPosition(Integer position) {
		this.position = position;
	}

	@Override
	public String toString() {
		StringBuilder ret = new StringBuilder("PageEntity{");
		ret.append("Key:").append(key).append(",");
		ret.append("Url:").append(url).append(",");
		ret.append("Title:").append(title).append(",");
		ret.append("Content:").append(content).append(",");
		ret.append("Visible:").append(visible).append(",");
		ret.append("Position:").append(position).append(",");
		ret.append("Tags:").append(pageTagListRef).append(",");
		ret.append("Template:").append(templateRef.getModel().getName()).append(",");
		ret.append("Author:").append(authorRef);
		ret.append("}");

		return ret.toString();
	}
}