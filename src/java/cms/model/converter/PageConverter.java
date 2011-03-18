package cms.model.converter;

import cms.model.model.PageEntity;
import java.util.Map;
import org.slim3.util.BeanUtil;

public class PageConverter implements IConverter {

	public PageEntity convert(Map<String, Object> input) {
		PageEntity pageEntity = new PageEntity();
		BeanUtil.copy(input, pageEntity);
		
		return pageEntity;
	}
	
}
