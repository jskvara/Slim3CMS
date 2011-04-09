package cms.model.service;

import cms.model.converter.TemplateConverter;
import cms.model.dao.TemplateDAO;
import cms.model.model.TemplateEntity;
import cms.model.validator.TemplateValidator;
import com.google.appengine.api.datastore.Key;
import java.util.Map;
import java.util.List;

public class TemplateService {

	private TemplateDAO templateDAO = new TemplateDAO();
	private TemplateConverter templateConverter = new TemplateConverter();
	private TemplateValidator templateValidator = new TemplateValidator();

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
		templateValidator.validate(templateEntity);
		
		return templateDAO.insert(templateEntity);
	}

	public TemplateEntity edit(Map<String, Object> input) throws ServiceException {
		TemplateEntity templateEntity = templateConverter.convert(input);
		templateValidator.validate(templateEntity);

		return templateDAO.edit(templateEntity);
	}

	public void delete(Key key) {
		templateDAO.delete(key);
	}
}