package cms.model.converter;

import cms.model.model.PageEntity;
import cms.model.model.TemplateEntity;
import cms.model.service.TemplateService;
import cms.util.GuiceUtil;
import java.util.Map;
import org.slim3.util.BeanUtil;

public class PageConverter implements IConverter {

	private TemplateService templateService = GuiceUtil.getService(TemplateService.class);

	public PageEntity convert(Map<String, Object> input) throws ConverterException {
		PageEntity pageEntity = new PageEntity();
		BeanUtil.copy(input, pageEntity);

		pageEntity.setUrl(pageEntity.getUrl().toLowerCase().trim());
		
		String templateName = (String)input.get("templateName");
		if(!templateName.equals("")) {
			TemplateEntity templateEntity = templateService.getTemplateByName(templateName);
			if (templateEntity != null) {
				pageEntity.getTemplateRef().setModel(templateEntity);
			} else {
				throw new ConverterException("templateName", "Tato šablona neexistuje.");
			}
		}

		return pageEntity;
	}
	
}
