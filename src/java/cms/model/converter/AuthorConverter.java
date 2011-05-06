package cms.model.converter;

import cms.model.model.AuthorEntity;
import java.util.Map;
import org.slim3.util.BeanUtil;

public class AuthorConverter implements IConverter {

	public AuthorEntity convert(Map<String, Object> input) {
		AuthorEntity authorEntity = new AuthorEntity();
		BeanUtil.copy(input, authorEntity);
		
		return authorEntity;
	}
	
}
