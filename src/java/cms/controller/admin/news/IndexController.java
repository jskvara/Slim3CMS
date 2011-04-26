package cms.controller.admin.news;

import cms.controller.admin.AdminController;
import cms.model.service.NewsService;
import cms.util.GuiceUtil;
import org.slim3.controller.Navigation;

public class IndexController extends AdminController {

	private NewsService newsService = GuiceUtil.getService(NewsService.class);

	@Override
	public Navigation run() throws Exception {
		requestScope("pageTitle", "Novinky");
		requestScope("news", newsService.getAllNews());

		return forward("/cms/admin/news/index.jsp");
	}
}
