package cms.model.model.dto;

import cms.model.model.PageEntity;
import cms.model.model.PageTagEntity;
import cms.model.model.TemplateEntity;
import com.google.appengine.api.datastore.KeyFactory;
import java.util.List;

public class PageDTO {
	protected PageEntity pageEntity;

	public PageDTO(PageEntity pageEntity) {
		this.pageEntity = pageEntity;
	}

	public String getKey() {
		return KeyFactory.keyToString(pageEntity.getKey());
	}

	public Long getVersion() {
		return pageEntity.getVersion();
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

	public boolean getVisible() {
		return pageEntity.getVisible();
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
		TemplateEntity templateEntity = pageEntity.getTemplateRef().getModel();
		TemplateDTO templateDTO = new TemplateDTO(templateEntity);
		
		return templateDTO.getContent();
	}

	public String[] getTags() {
		List<PageTagEntity> pageTagEntityies = pageEntity.getPageTagListRef().getModelList();
		String[] tags = new String[pageTagEntityies.size()];

		for (int i = 0; i < pageTagEntityies.size(); i++) {
			PageTagEntity pageTagEntity = pageTagEntityies.get(i);
			tags[i] = pageTagEntity.getTagRef().getModel().getName();
		}

		return tags;
	}
}
