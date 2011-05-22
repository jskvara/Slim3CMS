package cms.model.service;

import cms.model.converter.NewsConverter;
import cms.model.dao.NewsDAO;
import cms.model.model.NewsEntity;
import cms.model.validator.NewsValidator;
import com.google.appengine.api.datastore.Key;
import com.google.inject.Inject;
import java.util.ConcurrentModificationException;
import java.util.Map;
import java.util.List;

public class NewsService implements Service {

	@Inject
	private NewsDAO newsDAO;
	@Inject
	private NewsConverter newsConverter;
	@Inject
	private NewsValidator newsValidator;

	public List<NewsEntity> getAllNews() {
		return newsDAO.getAll();
	}

	public List<NewsEntity> getAllVisibleNews() {
		return newsDAO.getAllVisible();
	}

	public List<NewsEntity> getHomepageNews() {
		return newsDAO.getHomepage();
	}

	public NewsEntity getNews(Key key) {
		return newsDAO.get(key);
	}

	public NewsEntity insert(Map<String, Object> input) throws ServiceException {
		NewsEntity newsEntity = (NewsEntity) newsConverter.convert(input);
		newsValidator.validateAdd(newsEntity);

		return newsDAO.insert(newsEntity);
	}

	public NewsEntity edit(Map<String, Object> input) throws ServiceException {
		NewsEntity newsEntity = (NewsEntity) newsConverter.convert(input);
		newsValidator.validateEdit(newsEntity);

		NewsEntity editedNews = null;
		try {
			editedNews = newsDAO.edit(newsEntity);
		} catch (ConcurrentModificationException e) {
			throw new ServiceException("Novinku upravuje někdo jiný.");
		}

		return editedNews;
	}

	public void delete(Key key, Long version) throws ServiceException {
		try {
			newsDAO.delete(key, version);
		} catch (ConcurrentModificationException e) {
			throw new ServiceException("Novinku upravuje někdo jiný.");
		}
	}
}
