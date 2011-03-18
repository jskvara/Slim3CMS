package cms.model.converter;

import cms.model.model.NewsEntity;
import java.util.Map;
import org.slim3.util.BeanUtil;

public class NewsConverter implements IConverter {

	public NewsEntity convert(Map<String, Object> input) {
		NewsEntity newsEntity = new NewsEntity();
		BeanUtil.copy(input, newsEntity);
		
		return newsEntity;
	}
	
}
