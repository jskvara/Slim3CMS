package cms.controller.admin.author;

import cms.controller.admin.AdminController;
import cms.model.service.AuthorService;
import cms.util.GuiceUtil;
import org.slim3.controller.Navigation;

public class IndexController extends AdminController {

	private AuthorService authorService = GuiceUtil.getService(AuthorService.class);

	@Override
	public Navigation run() throws Exception {
		requestScope("pageTitle", "Autoři");
		requestScope("authors", authorService.getAllAuthors());

		return forward("/cms/admin/author/index.jsp");
	}
}
