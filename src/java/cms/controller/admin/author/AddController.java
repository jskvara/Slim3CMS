package cms.controller.admin.author;

import cms.controller.admin.AdminController;
import cms.model.service.ServiceException;
import cms.model.service.AuthorService;
import cms.util.GuiceUtil;
import cms.util.Messages;
import org.slim3.controller.Navigation;
import org.slim3.util.RequestMap;

public class AddController extends AdminController {

	private AuthorService authorService = GuiceUtil.getService(AuthorService.class);

	@Override
	public Navigation run() throws Exception {
		requestScope("pageTitle", "Přidat autora");
		
		if (param("submit") != null) {
			try {
				authorService.insert(new RequestMap(request));
			} catch(ServiceException e) {
				request.setAttribute("errors", e.getErrors());
				return forward("/cms/admin/author/add.jsp");
			}
			Messages.setSessionMessage("Autor byl přidán.");
			return redirect("/admin/author/");
		}
		
		return forward("/cms/admin/author/add.jsp");
	}
}