package cms.controller.admin.tag;

import cms.model.service.TagService;
import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

public class IndexController extends Controller {

	private TagService tagService = new TagService();

	@Override
	public Navigation run() throws Exception {
		requestScope("pageTitle", "Štítky");
		requestScope("tags", tagService.getAllTags());

		return forward("/cms/admin/tag/index.jsp");
	}
}
