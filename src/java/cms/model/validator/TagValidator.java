package cms.model.validator;

import cms.model.dao.TagDAO;
import cms.model.meta.TagEntityMeta;
import cms.model.model.TagEntity;
import com.google.appengine.api.datastore.Key;
import com.google.inject.Inject;

public class TagValidator extends AbstractValidator {

	protected TagEntityMeta meta = TagEntityMeta.get();
	@Inject
	protected TagDAO tagDAO;

	protected boolean validateFields() {
		boolean ret = true;
		validators.add(meta.name, validators.required(), validators.maxlength(255));

		if (isAdd()) {
			String name = (String) input.get(meta.name.toString());
			if (tagDAO.getByName(name) != null) {
				validators.getErrors().put(meta.name.toString(), "Tento štítek již existuje.");

				ret = false;
			}
		}

		if(isEdit()) {
			Key tagKey = (Key) input.get(meta.key.toString());
			TagEntity oldTagEntity = tagDAO.get(tagKey);
			String oldName = oldTagEntity.getName();
			String name = (String) input.get(meta.name.toString());
			if(!oldName.equals(name)) {
				if (tagDAO.getByName(name) != null) {
					validators.getErrors().put(meta.name.toString(), "Tento štítek již existuje.");

					ret = false;
				}
			}
		}

		return ret;
	}
}
