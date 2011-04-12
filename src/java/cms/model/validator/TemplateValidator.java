package cms.model.validator;

import cms.model.meta.TemplateEntityMeta;

public class TemplateValidator extends AbstractValidator {

	TemplateEntityMeta meta = TemplateEntityMeta.get();

	protected void validateFields() {
		validators.add(meta.name, validators.required(), validators.maxlength(255));
		// TODO unique
	}
}
