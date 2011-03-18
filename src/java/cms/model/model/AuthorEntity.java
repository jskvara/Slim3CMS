package cms.model.model;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.users.User;
import java.util.Date;
import org.slim3.datastore.Attribute;
import org.slim3.datastore.CreationDate;
import org.slim3.datastore.Model;

@Model(schemaVersion = 1)
public class AuthorEntity implements IEntity {
	private static final long serialVersionUID = 1L;

	@Attribute(primaryKey = true)
	private Key key;

	@Attribute(version = true)
	private Long version;

	private String name;
	private User user;

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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}