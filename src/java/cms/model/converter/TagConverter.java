package cms.model.converter;

import cms.model.model.TagEntity;
import java.util.Map;
import org.slim3.util.BeanUtil;

public class TagConverter implements IConverter {

	public TagEntity convert(Map<String, Object> input) {
		TagEntity tagEntity = new TagEntity();
		BeanUtil.copy(input, tagEntity);
		
		return tagEntity;
	}
	
}
