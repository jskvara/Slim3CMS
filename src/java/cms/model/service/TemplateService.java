package cms.model.service;

import cms.model.converter.TemplateConverter;
import cms.model.dao.TemplateDAO;
import cms.model.model.TemplateEntity;
import cms.model.validator.TemplateValidator;
import com.google.appengine.api.datastore.Key;
import com.google.inject.Inject;
import java.util.Map;
import java.util.List;

public class TemplateService implements Service {

	@Inject
	private TemplateDAO templateDAO;
	@Inject
	private TemplateConverter templateConverter;
	@Inject
	private TemplateValidator templateValidator;

	public List<TemplateEntity> getAllTemplates() {
		return templateDAO.getAll();
	}

	public TemplateEntity getTemplate(Key key) {
		return templateDAO.get(key);
	}

	public TemplateEntity getTemplateByName(String name) {
		return templateDAO.getByName(name);
	}

	public TemplateEntity insert(Map<String, Object> input) throws ServiceException {
		TemplateEntity templateEntity = templateConverter.convert(input);
		templateValidator.validateAdd(templateEntity);
		
		return templateDAO.insert(templateEntity);
	}

	public TemplateEntity edit(Map<String, Object> input) throws ServiceException {
		TemplateEntity templateEntity = templateConverter.convert(input);
		templateValidator.validateEdit(templateEntity);
		
		return templateDAO.edit(templateEntity);
	}

	public void delete(Key key) {
		templateDAO.delete(key);
	}
}