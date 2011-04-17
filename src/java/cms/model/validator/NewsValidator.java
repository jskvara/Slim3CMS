package cms.model.validator;

import cms.model.meta.NewsEntityMeta;

public class NewsValidator extends AbstractValidator {

	protected NewsEntityMeta meta = NewsEntityMeta.get();

	@Override
	protected boolean validateFields() {
		boolean ret = true;

		validators.add(meta.title, validators.required(), validators.maxlength(255));
		validators.add(meta.created, validators.required());

		return ret;
	}
}
