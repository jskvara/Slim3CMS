package cms.model.validator;

import cms.model.dao.TemplateDAO;
import cms.model.meta.TemplateEntityMeta;
import cms.model.model.TemplateEntity;
import com.google.appengine.api.datastore.Key;
import com.google.inject.Inject;

public class TemplateValidator extends AbstractValidator {

	protected TemplateEntityMeta meta = TemplateEntityMeta.get();
	@Inject
	protected TemplateDAO templateDAO;

	protected boolean validateFields() {
		boolean ret = true;
		validators.add(meta.name, validators.required(), validators.maxlength(255));

		if(isAdd()) {
			String name = (String) input.get(meta.name.toString());
			if (templateDAO.getByName(name) != null) {
				validators.getErrors().put(meta.name.toString(), "Tato šablona již existuje.");

				ret = false;
			}
		}

		if(isEdit()) {
			Key templateKey = (Key) input.get(meta.key.toString());
			TemplateEntity oldTemplateEntity = templateDAO.get(templateKey);
			String oldName = oldTemplateEntity.getName();
			String name = (String) input.get(meta.name.toString());
			if(!oldName.equals(name)) {
				if (templateDAO.getByName(name) != null) {
					validators.getErrors().put(meta.name.toString(), "Tato šablona již existuje.");

					ret = false;
				}
			}
		}

		return ret;
	}
}
