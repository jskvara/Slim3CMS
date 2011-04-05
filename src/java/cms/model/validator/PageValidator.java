package cms.model.validator;

import cms.model.meta.PageEntityMeta;

public class PageValidator extends AbstractValidator {

	protected PageEntityMeta meta = new PageEntityMeta();

	protected void validateFields() {
		validators.add(meta.url, validators.required(), validators.maxlength(255), validators.minlength(5));
	}
	
}
