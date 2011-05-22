package cms.model.service;

import cms.model.converter.AuthorConverter;
import cms.model.dao.AuthorDAO;
import cms.model.model.AuthorEntity;
import cms.model.validator.AuthorValidator;
import com.google.appengine.api.datastore.Key;
import com.google.inject.Inject;
import java.util.ConcurrentModificationException;
import java.util.Map;
import java.util.List;

public class AuthorService implements Service {

	@Inject
	private AuthorDAO authorDAO;
	@Inject
	private AuthorConverter authorConverter;
	@Inject
	private AuthorValidator authorValidator;

	public List<AuthorEntity> getAllAuthors() {
		return authorDAO.getAll();
	}

	public AuthorEntity getAuthor(Key key) {
		return authorDAO.get(key);
	}

	public AuthorEntity getAuthorByEmail(String email) {
		return authorDAO.getByEmail(email);
	}

	public boolean isAuthor(String email) {
		return (authorDAO.getByEmail(email) != null);
	}

	public AuthorEntity insert(Map<String, Object> input) throws ServiceException {
		AuthorEntity authorEntity = authorConverter.convert(input);
		authorValidator.validateAdd(authorEntity);

		return authorDAO.insert(authorEntity);
	}

	public AuthorEntity edit(Map<String, Object> input) throws ServiceException {
		AuthorEntity authorEntity = authorConverter.convert(input);
		authorValidator.validateEdit(authorEntity);

		AuthorEntity editedAuthor = null;
		try {
			editedAuthor = authorDAO.edit(authorEntity);
		} catch (ConcurrentModificationException e) {
			throw new ServiceException("Autora upravuje někdo jiný.");
		}

		return editedAuthor;
	}

	public void delete(Key key, Long version) throws ServiceException {
		try {
			authorDAO.delete(key, version);
		} catch (ConcurrentModificationException e) {
			throw new ServiceException("Autora upravuje někdo jiný.");
		}
	}
}
