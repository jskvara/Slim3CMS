package cms.controller.admin.page;

import cms.controller.admin.AdminController;
import cms.model.meta.PageEntityMeta;
import cms.model.model.PageEntity;
import cms.model.service.PageService;
import cms.model.service.ServiceException;
import cms.util.GuiceUtil;
import cms.util.Message;
import cms.util.Messages;
import com.google.appengine.api.datastore.Key;
import org.slim3.controller.Navigation;

public class DeleteController extends AdminController {

	private PageService pageService = GuiceUtil.getService(PageService.class);
	private PageEntityMeta pageMeta = PageEntityMeta.get();

	@Override
	public Navigation run() throws Exception {
		requestScope("pageTitle", "Smazat stránku");

		if (param("submit") != null) {
			Key key = asKey(pageMeta.key);
			Long version = asLong(pageMeta.version);
			if (key == null || version == null) {
				Messages.setSessionMessage("Stránka neexistuje.", Message.ERROR);
				return redirect("/admin/page/");
			}

			try {
				pageService.delete(key, version);
			} catch (ServiceException e) {
				Messages.setSessionMessage(e.getMessage(), Message.ERROR);

				return redirect("/admin/page/");
			}
			Messages.setSessionMessage("Stránka byla smazána.");

			return redirect("/admin/page/");
		}

		Key key = asKey(pageMeta.key);
		if (key == null) {
			Messages.setSessionMessage("Stránka neexistuje.", Message.ERROR);
			return redirect("/admin/page/");
		}
		PageEntity pageEntity = pageService.getPage(key);
		requestScope("entity", pageEntity);
		requestScope("version", pageEntity.getVersion());

		return forward("/cms/admin/page/delete.jsp");
	}
}
