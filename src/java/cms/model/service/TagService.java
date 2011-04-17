package cms.model.service;

import cms.model.converter.TagConverter;
import cms.model.dao.TagDAO;
import cms.model.model.TagEntity;
import cms.model.validator.TagValidator;
import com.google.appengine.api.datastore.Key;
import com.google.inject.Inject;
import java.util.Map;
import java.util.List;

public class TagService implements Service {

	@Inject
	private TagDAO tagDAO = new TagDAO();
	@Inject
	private TagConverter tagConverter = new TagConverter();
	@Inject
	private TagValidator tagValidator = new TagValidator();

	public List<TagEntity> getAllTags() {
		return tagDAO.getAll();
	}

	public TagEntity getTag(Key key) {
		return tagDAO.get(key);
	}

	public TagEntity getTagByName(String name) {
		return tagDAO.getByName(name);
	}

	public TagEntity insert(Map<String, Object> input) throws ServiceException {
		TagEntity tagEntity = tagConverter.convert(input);
		tagValidator.validateAdd(tagEntity);
		
		return tagDAO.insert(tagEntity);
	}

	public TagEntity edit(Map<String, Object> input) throws ServiceException {
		TagEntity tagEntity = tagConverter.convert(input);
		tagValidator.validateEdit(tagEntity);
		
		return tagDAO.edit(tagEntity);
	}

	public void delete(Key key) {
		tagDAO.delete(key);
	}
}