package cms.model.service;

import cms.model.converter.PageConverter;
import cms.model.dao.PageDAO;
import cms.model.model.PageEntity;
import cms.model.validator.PageValidator;
import com.google.appengine.api.datastore.Key;
import com.google.inject.Inject;
import java.util.Map;
import java.util.List;

public class PageService implements Service {
	@Inject
	private PageDAO pageDAO;
	@Inject
	private PageConverter pageConverter;
	@Inject
	private PageValidator pageValidator;

	public List<PageEntity> getAllPages() {
		return pageDAO.getAll();
	}

	public PageEntity getPage(Key key) {
		return pageDAO.get(key);
	}

	public PageEntity getPageByUrl(String url) {
		return pageDAO.getByUrl(url);
	}

	public PageEntity getVisiblePageByUrl(String url) {
		return pageDAO.getVisibleByUrl(url);
	}

	public PageEntity insert(Map<String, Object> input) throws ServiceException {
		PageEntity pageEntity = pageConverter.convert(input);
		pageValidator.validateAdd(pageEntity);
		
		return pageDAO.insert(pageEntity);
	}

	public PageEntity edit(Map<String, Object> input) throws ServiceException {
		PageEntity pageEntity = pageConverter.convert(input);
		pageValidator.validateEdit(pageEntity);
		
		return pageDAO.edit(pageEntity);
	}

	public void delete(Key key) {
		pageDAO.delete(key);
	}
}