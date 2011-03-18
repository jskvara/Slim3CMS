package cms.model.converter;

import cms.model.model.TemplateEntity;
import java.util.Map;
import org.slim3.util.BeanUtil;

public class TemplateConverter implements IConverter {

	public TemplateEntity convert(Map<String, Object> input) {
		TemplateEntity templateEntity = new TemplateEntity();
		BeanUtil.copy(input, templateEntity);
		
		return templateEntity;
	}
	
}
