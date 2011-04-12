package cms.model.validator;

import cms.model.meta.TagEntityMeta;

public class TagValidator extends AbstractValidator {

	protected TagEntityMeta meta = TagEntityMeta.get();

	protected void validateFields() {
		validators.add(meta.name, validators.required(), validators.maxlength(255));
		// TODO unique
	}
}
