package cms.model.service;

import cms.model.converter.TagConverter;
import cms.model.dao.TagDAO;
import cms.model.model.TagEntity;
import cms.model.validator.TagValidator;
import cms.model.validator.ValidatorException;
import com.google.appengine.api.datastore.Key;
import java.util.Map;
import java.util.List;

public class TagService {

	private TagDAO tagDAO = new TagDAO();
	private TagConverter tagConverter = new TagConverter();
	private TagValidator tagValidator = new TagValidator();

	public List<TagEntity> getAllTags() {
		return tagDAO.getAll();
	}

	public TagEntity getTag(Key key) {
		return tagDAO.get(key);
	}

	public TagEntity insert(Map<String, Object> input) throws ServiceException {
		TagEntity tagEntity = tagConverter.convert(input);
		tagValidator.validate(tagEntity);
		
		return tagDAO.insert(tagEntity);
	}

	public TagEntity edit(Map<String, Object> input) throws ServiceException {
		TagEntity tagEntity = tagConverter.convert(input);
		tagValidator.validate(tagEntity);
		
		return tagDAO.edit(tagEntity);
	}

	public void delete(Key key) {
		tagDAO.delete(key);
	}
}