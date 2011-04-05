package cms.controller.admin.page;

import cms.model.service.PageService;
import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

public class IndexController extends Controller {

	private PageService pageService = new PageService();

	@Override
	public Navigation run() throws Exception {
		requestScope("title", "Stránky");
		requestScope("pages", pageService.getAllPages());

		return forward("/cms/admin/page/index.jsp");
	}
}
