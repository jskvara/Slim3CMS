package cms.controller.admin.news;

import cms.model.service.NewsService;
import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

public class IndexController extends Controller {

	private NewsService newsService = new NewsService();

	@Override
	public Navigation run() throws Exception {
		requestScope("pageTitle", "Novinky");
		requestScope("news", newsService.getAllNews());

		return forward("/cms/admin/news/index.jsp");
	}
}
