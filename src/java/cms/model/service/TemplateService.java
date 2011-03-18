package cms.model.service;

import cms.model.converter.TemplateConverter;
import cms.model.dao.TemplateDAO;
import cms.model.model.TemplateEntity;
import cms.model.validator.TemplateValidator;
import cms.model.validator.ValidatorException;
import com.google.appengine.api.datastore.Key;
import java.util.Map;
import java.util.List;

public class TemplateService {

	private TemplateDAO templateDAO = new TemplateDAO();
	private TemplateConverter templateConverter = new TemplateConverter();
	private TemplateValidator templateValidator = new TemplateValidator();

	public List<TemplateEntity> getAllTemplate() {
		return templateDAO.getAll();
	}

	public TemplateEntity getTemplate(Key key) {
		return templateDAO.get(key);
	}

	public void insert(Map<String, Object> input) {
		TemplateEntity templateEntity = templateConverter.convert(input);
		try {
			templateValidator.validate(templateEntity);
		} catch(ValidatorException ex) {
			// TODO
		}

		templateDAO.insert(templateEntity);
	}

	public void edit(Map<String, Object> input) {
		TemplateEntity templateEntity = templateConverter.convert(input);
		try {
			templateValidator.validate(templateEntity);
		} catch(ValidatorException ex) {
		}

		templateDAO.edit(templateEntity);
	}

	public void delete(Key key) {
		templateDAO.delete(key);
	}
}