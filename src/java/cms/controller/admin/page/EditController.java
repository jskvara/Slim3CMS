package cms.controller.admin.page;

import cms.model.meta.PageEntityMeta;
import cms.model.model.PageEntity;
import cms.model.service.PageService;
import cms.model.service.ServiceException;
import cms.util.Messages;
import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.util.BeanUtil;
import org.slim3.util.RequestMap;

public class EditController extends Controller {

	private PageService pageService = new PageService();
	private PageEntityMeta pageMeta = PageEntityMeta.get();

	@Override
	public Navigation run() throws Exception {
		requestScope("title", "Upravit stránku");

		if (request.getParameter("submit") != null) {
			try {
//				RequestMap requestMap = new RequestMap(request);
//				requestMap.put("key", asKey(pageMeta.key));
//				requestMap.put("version", pageMeta.version);
				pageService.edit(new RequestMap(request));
			} catch(ServiceException e) {
				request.setAttribute("errors", e.getErrors());
				return forward("/cms/admin/page/edit.jsp");
			}
			Messages.setSessionMessage("Stránka byla upravena.");
			return redirect("/admin/page/");
		}

		PageEntity pageEntity = pageService.getPage(asKey(pageMeta.key));
		BeanUtil.copy(pageEntity, request);
		return forward("/cms/admin/page/edit.jsp");
	}
}
