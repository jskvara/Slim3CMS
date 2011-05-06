package cms.controller.admin.author;

import cms.controller.admin.AdminController;
import cms.model.meta.AuthorEntityMeta;
import cms.model.model.AuthorEntity;
import cms.model.service.ServiceException;
import cms.model.service.AuthorService;
import cms.util.GuiceUtil;
import cms.util.Message;
import cms.util.Messages;
import com.google.appengine.api.datastore.Key;
import org.slim3.controller.Navigation;

public class DeleteController extends AdminController {

	private AuthorService authorService = GuiceUtil.getService(AuthorService.class);
	private AuthorEntityMeta authorMeta = AuthorEntityMeta.get();

	@Override
	public Navigation run() throws Exception {
		requestScope("pageTitle", "Smazat autora");

		if (param("submit") != null) {
			Key key = asKey(authorMeta.key);
			Long version = asLong(authorMeta.version);
			if (key == null || version == null) {
				Messages.setSessionMessage("Autor neexistuje.", Message.ERROR);
				return redirect("/admin/author/");
			}
			try {
				authorService.delete(key, version);
			} catch (ServiceException e) {
				Messages.setSessionMessage(e.getMessage(), Message.ERROR);

				return redirect("/admin/author/");
			}
			Messages.setSessionMessage("Autor byl smazán.");

			return redirect("/admin/author/");
		}

		Key key = asKey(authorMeta.key);
		if (key == null) {
			Messages.setSessionMessage("Autor neexistuje.", Message.ERROR);
			return redirect("/admin/author/");
		}
		AuthorEntity authorEntity = authorService.getAuthor(key);
		requestScope("entity", authorEntity);
		requestScope("version", authorEntity.getVersion());

		return forward("/cms/admin/author/delete.jsp");
	}
}
