package cms.controller;

import cms.model.service.PageService;
import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

public class IndexController extends Controller {

	private PageService pageService = new PageService();

	@Override
	public Navigation run() throws Exception {
		requestScope("pages", pageService.getAllPages());
		return forward("/cms/index.jsp");
		//PrintWriter out = response.getWriter();out.println("aaa");
	}
}
