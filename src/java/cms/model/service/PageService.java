package cms.model.service;

import cms.model.converter.PageConverter;
import cms.model.dao.PageDAO;
import cms.model.model.PageEntity;
import cms.model.validator.PageValidator;
import com.google.appengine.api.datastore.Key;
import java.util.Map;
import java.util.List;

public class PageService {
	private PageDAO pageDAO = new PageDAO();
	private PageConverter pageConverter = new PageConverter();
	private PageValidator pageValidator = new PageValidator();

	public List<PageEntity> getAllPages() {
		return pageDAO.getAll();
	}

	public PageEntity getPage(Key key) {
		return pageDAO.get(key);
	}

	public PageEntity getPageByUrl(String url) {
		return pageDAO.getByUrl(url);
	}

	public PageEntity insert(Map<String, Object> input) throws ServiceException {
		PageEntity pageEntity = pageConverter.convert(input);
		pageValidator.validate(pageEntity);
		
		return pageDAO.insert(pageEntity);
	}

	public PageEntity edit(Map<String, Object> input) throws ServiceException {
		PageEntity pageEntity = pageConverter.convert(input);
		pageValidator.validate(pageEntity);
		
		return pageDAO.edit(pageEntity);
	}

	public void delete(Key key) {
		pageDAO.delete(key);
	}
}