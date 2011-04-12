package cms.controller.admin.news;

import cms.model.service.NewsService;
import cms.model.service.ServiceException;
import cms.util.DateUtil;
import cms.util.Messages;
import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.util.RequestMap;

public class AddController extends Controller {

	private NewsService newsService = new NewsService();

	@Override
	public Navigation run() throws Exception {
		requestScope("pageTitle", "Přidat štítek");
		
		if (param("submit") != null) {
			try {
				newsService.insert(new RequestMap(request));
			} catch(ServiceException e) {
				request.setAttribute("errors", e.getErrors());
				return forward("/cms/admin/tag/add.jsp");
			}
			Messages.setSessionMessage("Štítek byl přidán.");
			return redirect("/admin/tag/");
		}
		
		return forward("/cms/admin/news/add.jsp");
	}
}