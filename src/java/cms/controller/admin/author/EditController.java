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
import org.slim3.util.BeanUtil;
import org.slim3.util.RequestMap;

public class EditController extends AdminController {

	private AuthorService authorService = GuiceUtil.getService(AuthorService.class);
	private AuthorEntityMeta authorMeta = AuthorEntityMeta.get();

	@Override
	public Navigation run() throws Exception {
		requestScope("pageTitle", "Upravit autora");

		if (param("submit") != null) {
			try {
				authorService.edit(new RequestMap(request));
			} catch (ServiceException e) {
				if (e.getErrors() != null) {
					requestScope("errors", e.getErrors());
				} else {
					Messages.setRequestMessage(e.getMessage(), Message.ERROR);
				}

				return forward("/cms/admin/author/edit.jsp");
			}
			Messages.setSessionMessage("Autor byl upraven.");
			return redirect("/admin/author/");
		}

		Key key = asKey(authorMeta.key);
		if (key == null) {
			Messages.setSessionMessage("Chybný parametr.", Message.ERROR);
			return redirect("/admin/author/");
		}
		AuthorEntity authorEntity = authorService.getAuthor(key);
		BeanUtil.copy(authorEntity, request);

		return forward("/cms/admin/author/edit.jsp");
	}
}
