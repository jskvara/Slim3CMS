package cms.controller.admin.tag;

import cms.controller.admin.AdminController;
import cms.model.meta.TagEntityMeta;
import cms.model.model.TagEntity;
import cms.model.service.ServiceException;
import cms.model.service.TagService;
import cms.util.GuiceUtil;
import cms.util.Message;
import cms.util.Messages;
import com.google.appengine.api.datastore.Key;
import org.slim3.controller.Navigation;

public class DeleteController extends AdminController {

	private TagService tagService = GuiceUtil.getService(TagService.class);
	private TagEntityMeta tagMeta = TagEntityMeta.get();

	@Override
	public Navigation run() throws Exception {
		requestScope("pageTitle", "Smazat štítek");

		if (param("submit") != null) {
			Key key = asKey(tagMeta.key);
			Long version = asLong(tagMeta.version);
			if (key == null || version == null) {
				Messages.setSessionMessage("Štítek neexistuje.", Message.ERROR);
				return redirect("/admin/tag/");
			}
			try {
				tagService.delete(key, version);
			} catch (ServiceException e) {
				Messages.setSessionMessage(e.getMessage(), Message.ERROR);

				return redirect("/admin/tag/");
			}
			Messages.setSessionMessage("Štítek byl smazán.");

			return redirect("/admin/tag/");
		}

		Key key = asKey(tagMeta.key);
		if (key == null) {
			Messages.setSessionMessage("Štítek neexistuje.", Message.ERROR);
			return redirect("/admin/tag/");
		}
		TagEntity tagEntity = tagService.getTag(key);
		requestScope("entity", tagEntity);
		requestScope("version", tagEntity.getVersion());

		return forward("/cms/admin/tag/delete.jsp");
	}
}
