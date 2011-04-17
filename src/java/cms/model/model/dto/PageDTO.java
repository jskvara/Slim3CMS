package cms.model.model.dto;

import cms.model.model.PageEntity;
import com.google.appengine.api.datastore.KeyFactory;

public class PageDTO {
	protected PageEntity pageEntity;

	public PageDTO(PageEntity pageEntity) {
		this.pageEntity = pageEntity;
	}

	public String getKey() {
		return KeyFactory.keyToString(pageEntity.getKey());
	}

	public String getUrl() {
		return pageEntity.getUrl();
	}

	public String getTitle() {
		return pageEntity.getTitle();
	}

	public String getContent() {
		return pageEntity.getContent();
	}

	public String getVisible() {
		return (pageEntity.getVisible() ? "Yes" : "No");
	}

	public String getTemplateName() {
		if (pageEntity.getTemplateRef().getModel() == null) {
			return "";
		}
		return pageEntity.getTemplateRef().getModel().getName();
	}

	public String getTemplateContent() {
		if (pageEntity.getTemplateRef().getModel() == null) {
			return "";
		}
		return pageEntity.getTemplateRef().getModel().getContent();
	}
}
