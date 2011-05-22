package cms.model.model.dto;

import cms.model.model.NewsEntity;
import cms.util.DateUtil;
import com.google.appengine.api.datastore.KeyFactory;

public class NewsDTO {
	protected NewsEntity newsEntity;

	public NewsDTO(NewsEntity newsEntity) {
		this.newsEntity = newsEntity;
	}

	public String getKey() {
		return KeyFactory.keyToString(newsEntity.getKey());
	}

	public Long getVersion() {
		return newsEntity.getVersion();
	}

	public String getTitle() {
		return newsEntity.getTitle();
	}

	public String getText() {
		return newsEntity.getText();
	}

	public String getCreated() {
		return DateUtil.dateToString(newsEntity.getCreated());
	}

	public String getVisible() {
		return (newsEntity.getVisible() ? "true" : "false");
	}
}
