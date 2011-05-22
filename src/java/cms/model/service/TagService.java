package cms.model.service;

import cms.model.converter.TagConverter;
import cms.model.dao.TagDAO;
import cms.model.model.TagEntity;
import cms.model.validator.TagValidator;
import com.google.appengine.api.datastore.Key;
import com.google.inject.Inject;
import java.util.ConcurrentModificationException;
import java.util.Map;
import java.util.List;

public class TagService implements Service {

	@Inject
	private TagDAO tagDAO;
	@Inject
	private TagConverter tagConverter;
	@Inject
	private TagValidator tagValidator;

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

		TagEntity editedTag = null;
		try {
			editedTag = tagDAO.edit(tagEntity);
		} catch (ConcurrentModificationException e) {
			throw new ServiceException("Štítek upravuje někdo jiný.");
		}

		return editedTag;
	}

	public void delete(Key key, Long version) throws ServiceException {
		try {
			tagDAO.delete(key, version);
		} catch (ConcurrentModificationException e) {
			throw new ServiceException("Štítek upravuje někdo jiný.");
		}
	}
}
