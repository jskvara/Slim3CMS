package cms.model.converter;

import cms.model.model.TemplateEntity;
import com.google.appengine.api.datastore.Text;
import java.util.Map;
import org.slim3.util.BeanUtil;

public class TemplateConverter implements IConverter {

	public TemplateEntity convert(Map<String, Object> input) {
		TemplateEntity templateEntity = new TemplateEntity();
		BeanUtil.copy(input, templateEntity);

		Text content = new Text("");
		if (input.get("content") != null) {
			content = new Text((String) input.get("content"));
		}
		templateEntity.setContent(content);
		
		return templateEntity;
	}
}
