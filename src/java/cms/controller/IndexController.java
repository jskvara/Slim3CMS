package cms.controller;

import cms.model.model.PageEntity;
import cms.model.service.PageService;
import cms.util.GuiceUtil;
import java.io.PrintWriter;
import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

public class IndexController extends Controller {

	private PageService pageService = GuiceUtil.getService(PageService.class);

	@Override
	public Navigation run() throws Exception {
		//requestScope("pages", pageService.getAllPages());
		//return forward("/cms/index.jsp");

		// TODO add filter, set path, redirect to controller
		String url = request.getServletPath();
		PageEntity page = pageService.getPageByUrl(url);

		PrintWriter out = response.getWriter();
		out.println(page);

		return null;
	}
}
