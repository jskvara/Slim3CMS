package cms.model.converter;

import cms.model.model.PageEntity;
import cms.model.model.TemplateEntity;
import cms.model.service.TemplateService;
import java.util.Map;
import org.slim3.util.BeanUtil;

public class PageConverter implements IConverter {

	public PageEntity convert(Map<String, Object> input) {
		PageEntity pageEntity = new PageEntity();
		BeanUtil.copy(input, pageEntity);

		TemplateService templateService = new TemplateService();
		TemplateEntity templateEntity =
				templateService.getTemplateByName((String)input.get("template"));
		if (templateEntity != null) {
			pageEntity.getTemplateRef().setModel(templateEntity);
		}

		return pageEntity;
	}
	
}
