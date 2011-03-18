package cms.model.service;

import cms.model.converter.NewsConverter;
import cms.model.dao.NewsDAO;
import cms.model.model.NewsEntity;
import cms.model.validator.NewsValidator;
import cms.model.validator.ValidatorException;
import com.google.appengine.api.datastore.Key;
import java.util.Map;
import java.util.List;

public class NewsService {

	private NewsDAO newsDAO = new NewsDAO();
	private NewsConverter newsConverter = new NewsConverter();
	private NewsValidator newsValidator = new NewsValidator();

	public List<NewsEntity> getAllNews() {
		return newsDAO.getAll();
	}

	public NewsEntity getNews(Key key) {
		return newsDAO.get(key);
	}

	public void insert(Map<String, Object> input) {
		NewsEntity newsEntity = newsConverter.convert(input);
		try {
			newsValidator.validate(newsEntity);
		} catch(ValidatorException ex) {
			// TODO
		}
		
		newsDAO.insert(newsEntity);
	}

	public void edit(Map<String, Object> input) {
		NewsEntity newsEntity = newsConverter.convert(input);
		try {
			newsValidator.validate(newsEntity);
		} catch(ValidatorException ex) {
		}
		
		newsDAO.edit(newsEntity);
	}

	public void delete(Key key) {
		newsDAO.delete(key);
	}
}