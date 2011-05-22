package cms.model.validator;

import cms.model.dao.PageDAO;
import cms.model.meta.PageEntityMeta;
import cms.model.model.PageEntity;
import com.google.appengine.api.datastore.Key;
import com.google.inject.Inject;

public class PageValidator extends AbstractValidator {

	protected PageEntityMeta meta = PageEntityMeta.get();
	@Inject
	protected PageDAO pageDAO;
	
	protected boolean validateFields() {		
		validators.add(meta.url, validators.required(), validators.maxlength(255));

		String url = (String) input.get(meta.url.toString());
		if (url.startsWith("/") || url.endsWith("/")) {
			validators.getErrors().put(meta.url.toString(), "Url nesmí začínat nebo končit '/'.");
		}

		if (url.startsWith("admin/") || url.startsWith("_ah/")) {
			validators.getErrors().put(meta.url.toString(), "Tato url je rezervovaná.");
		}

		if(isAdd()) {
			if (pageDAO.getByUrl(url) != null) {
				validators.getErrors().put(meta.url.toString(), "Tato url již existuje.");
			}
		}

		if(isEdit()) {
			Key pageKey = (Key) input.get(meta.key.toString());
			PageEntity oldPageEntity = pageDAO.get(pageKey);
			String oldUrl = oldPageEntity.getUrl();
			if(!oldUrl.equals(url)) {
				if (pageDAO.getByUrl(url) != null) {
					validators.getErrors().put(meta.url.toString(), "Tato url již existuje.");
				}
			}
		}

		validators.add(meta.title, validators.maxlength(255));

		return validators.getErrors().isEmpty();
	}
	
}
