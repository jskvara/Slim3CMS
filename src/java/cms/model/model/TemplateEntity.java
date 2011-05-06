package cms.model.model;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Text;
import org.slim3.datastore.Attribute;
import org.slim3.datastore.Model;

@Model(schemaVersion = 1)
public class TemplateEntity implements IEntity {
	private static final long serialVersionUID = 1L;

	@Attribute(primaryKey = true)
	private Key key;

	@Attribute(version = true)
	private Long version;

	private String name;
	private Text content;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Text getContent() {
		return content;
	}

	public void setContent(Text content) {
		this.content = content;
	}

	public void setContent(String content) {
		this.content = new Text(content);
	}

	@Override
	public String toString() {
		StringBuilder ret = new StringBuilder("TemplateEntity{");
		ret.append("Key:").append(key).append(",");
		ret.append("Version:").append(version).append(",");
		ret.append("Name:").append(name).append(",");
		ret.append("Content:");
		if (content != null) {
			ret.append(content.getValue());
		}
		ret.append("}");

		return ret.toString();
	}
}