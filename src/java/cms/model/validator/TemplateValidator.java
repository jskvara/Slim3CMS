package cms.model.validator;

import cms.model.meta.TemplateEntityMeta;
import cms.model.model.TemplateEntity;
import cms.model.service.TemplateService;
import com.google.appengine.api.datastore.Key;
import com.google.inject.Inject;

public class TemplateValidator extends AbstractValidator {

	protected TemplateEntityMeta meta = TemplateEntityMeta.get();
	@Inject
	protected TemplateService templateService;

	protected boolean validateFields() {
		boolean ret = true;
		validators.add(meta.name, validators.required(), validators.maxlength(255));

		if(isAdd()) {
			String name = (String) input.get(meta.name.toString());
			if (templateService.getTemplateByName(name) != null) {
				validators.getErrors().put(meta.name.toString(), "Tato šablona již existuje.");

				ret = false;
			}
		}

		if(isEdit()) {
			Key templateKey = (Key) input.get(meta.key.toString());
			TemplateEntity oldTemplateEntity = templateService.getTemplate(templateKey);
			String oldName = oldTemplateEntity.getName();
			String name = (String) input.get(meta.name.toString());
			if(!oldName.equals(name)) {
				if (templateService.getTemplateByName(name) != null) {
					validators.getErrors().put(meta.name.toString(), "Tato šablona již existuje.");

					ret = false;
				}
			}
		}

		return ret;
	}
}
