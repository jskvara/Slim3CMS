package cms.controller.admin.page;

import cms.model.service.PageService;
import cms.util.GuiceUtil;
import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

public class IndexController extends Controller {

	private PageService pageService = GuiceUtil.getService(PageService.class);

	@Override
	public Navigation run() throws Exception {
		requestScope("pageTitle", "Stránky");
		requestScope("pages", pageService.getAllPages());

		return forward("/cms/admin/page/index.jsp");
	}
}
