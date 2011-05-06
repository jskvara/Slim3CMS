package cms.model.model.dto;

import cms.model.model.TemplateEntity;
import com.google.appengine.api.datastore.KeyFactory;

public class TemplateDTO {
	protected TemplateEntity templateEntity;

	public TemplateDTO(TemplateEntity templateEntity) {
		this.templateEntity = templateEntity;
	}

	public String getKey() {
		return KeyFactory.keyToString(templateEntity.getKey());
	}

	public Long getVersion() {
		return templateEntity.getVersion();
	}

	public String getName() {
		return templateEntity.getName();
	}

	public String getContent() {
		if (templateEntity.getContent() == null) {
			return "";
		}
		return templateEntity.getContent().getValue();
	}
}
