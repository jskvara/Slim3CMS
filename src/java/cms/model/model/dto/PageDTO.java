package cms.model.model.dto;

import cms.model.model.PageEntity;
import cms.model.model.PageTagEntity;
import cms.model.model.TagEntity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import java.util.List;
import org.apache.tools.ant.Project;
import org.slim3.datastore.Datastore;
import org.slim3.datastore.InverseModelListRef;

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
