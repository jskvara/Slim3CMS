package cms.controller.admin.template;

import cms.controller.admin.AdminController;
import cms.model.service.ServiceException;
import cms.model.service.TemplateService;
import cms.util.GuiceUtil;
import cms.util.Messages;
import org.slim3.controller.Navigation;
import org.slim3.util.RequestMap;

public class AddController extends AdminController {

	private TemplateService templateService = GuiceUtil.getService(TemplateService.class);

	@Override
	public Navigation run() throws Exception {
		requestScope("pageTitle", "Přidat šablonu");
		
		if (param("submit") != null) {
			try {
				templateService.insert(new RequestMap(request));
			} catch(ServiceException e) {
				request.setAttribute("errors", e.getErrors());
				return forward("/cms/admin/template/add.jsp");
			}
			Messages.setSessionMessage("Šablona byla přidána.");
			return redirect("/admin/template/");
		}
		
		return forward("/cms/admin/template/add.jsp");
	}
}