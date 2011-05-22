package cms.model.validator;

import cms.model.dao.AuthorDAO;
import cms.model.meta.AuthorEntityMeta;
import cms.model.model.AuthorEntity;
import com.google.appengine.api.datastore.Key;
import com.google.inject.Inject;

public class AuthorValidator extends AbstractValidator {

	protected AuthorEntityMeta meta = AuthorEntityMeta.get();
	@Inject
	protected AuthorDAO authorDAO;

	protected boolean validateFields() {
		boolean ret = true;
		validators.add(meta.email, validators.required(), validators.maxlength(255));

		if (isAdd()) {
			String email = (String) input.get(meta.email.toString());
			if (authorDAO.getByEmail(email) != null) {
				validators.getErrors().put(meta.email.toString(), "Tento autor již existuje.");

				ret = false;
			}
		}

		if(isEdit()) {
			Key authorKey = (Key) input.get(meta.key.toString());
			AuthorEntity oldAuthorEntity = authorDAO.get(authorKey);
			String oldName = oldAuthorEntity.getEmail();
			String email = (String) input.get(meta.email.toString());
			if(!oldName.equals(email)) {
				if (authorDAO.getByEmail(email) != null) {
					validators.getErrors().put(meta.email.toString(), "Tento autor již existuje.");

					ret = false;
				}
			}
		}

		return ret;
	}
}
