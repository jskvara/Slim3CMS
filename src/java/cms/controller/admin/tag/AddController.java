package cms.controller.admin.tag;

import cms.controller.admin.AdminController;
import cms.model.service.ServiceException;
import cms.model.service.TagService;
import cms.util.GuiceUtil;
import cms.util.Messages;
import org.slim3.controller.Navigation;
import org.slim3.util.RequestMap;

public class AddController extends AdminController {

	private TagService tagService = GuiceUtil.getService(TagService.class);

	@Override
	public Navigation run() throws Exception {
		requestScope("pageTitle", "Přidat štítek");
		
		if (param("submit") != null) {
			try {
				tagService.insert(new RequestMap(request));
			} catch(ServiceException e) {
				request.setAttribute("errors", e.getErrors());
				return forward("/cms/admin/tag/add.jsp");
			}
			Messages.setSessionMessage("Štítek byl přidán.");
			return redirect("/admin/tag/");
		}
		
		return forward("/cms/admin/tag/add.jsp");
	}
}