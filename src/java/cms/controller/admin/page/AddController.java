package cms.controller.admin.page;

import cms.model.service.PageService;
import cms.model.service.ServiceException;
import cms.util.Messages;
import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.util.RequestMap;

public class AddController extends Controller {

	private PageService pageService = new PageService();

	@Override
	public Navigation run() throws Exception {
		requestScope("pageTitle", "Přidat stránku");
		
		if (param("submit") != null) {
			try {
				pageService.insert(new RequestMap(request));
			} catch(ServiceException e) {
				request.setAttribute("errors", e.getErrors());
				return forward("/cms/admin/page/add.jsp");
			}
			Messages.setSessionMessage("Stránka byla přidána.");
			return redirect("/admin/page/");
		}
		
		return forward("/cms/admin/page/add.jsp");
	}
}