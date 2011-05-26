package cms.controller.admin.page;

import cms.controller.admin.AdminController;
import cms.model.service.PageService;
import cms.model.service.ServiceException;
import cms.model.service.TemplateService;
import cms.util.GuiceUtil;
import cms.util.Messages;
import org.slim3.controller.Navigation;
import org.slim3.util.RequestMap;

public class AddController extends AdminController {

	private PageService pageService = GuiceUtil.getService(PageService.class);
	private TemplateService templateService = GuiceUtil.getService(TemplateService.class);
	
	@Override
	public Navigation run() throws Exception {
		requestScope("pageTitle", "Přidat stránku");
		
		if (param("submit") != null) {
			try {
				pageService.insert(new RequestMap(request));

/*				Map<String, Object> params = new HashMap<String, Object>();
				params.put("title", "title");
				params.put("content", "content");
				params.put("visible", true);
				params.put("templateName", "testLayout");
				for (int i = Integer.valueOf(request.getParameter("url")); i < 10000; i++) {
					params.put("url", "page"+ i);
					pageService.insert(params);
				}*/
			} catch(ServiceException e) {
				request.setAttribute("errors", e.getErrors());
				requestScope("templates", templateService.getAllTemplates());

				return forward("/cms/admin/page/add.jsp");
			}
			Messages.setSessionMessage("Stránka byla přidána.");
			return redirect("/admin/page/");
		}

		requestScope("visible", true);
		requestScope("templates", templateService.getAllTemplates());
		
		return forward("/cms/admin/page/add.jsp");
	}
}