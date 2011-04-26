package cms.controller.admin;

import org.slim3.controller.Navigation;

public class IndexController extends AdminController {

	@Override
	public Navigation run() throws Exception {
		return forward("/cms/admin/index.jsp");
	}

}
