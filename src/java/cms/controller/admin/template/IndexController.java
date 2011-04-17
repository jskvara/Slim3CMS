package cms.controller.admin.template;

import cms.model.service.TemplateService;
import cms.util.GuiceUtil;
import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

public class IndexController extends Controller {

	private TemplateService templateService = GuiceUtil.getService(TemplateService.class);

	@Override
	public Navigation run() throws Exception {
		requestScope("pageTitle", "Šablony");
		requestScope("templates", templateService.getAllTemplates());

		return forward("/cms/admin/template/index.jsp");
	}
}
