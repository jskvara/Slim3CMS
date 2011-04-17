package cms.controller.admin.page;

import cms.model.meta.PageEntityMeta;
import cms.model.model.PageEntity;
import cms.model.service.PageService;
import cms.util.GuiceUtil;
import cms.util.Message;
import cms.util.Messages;
import com.google.appengine.api.datastore.Key;
import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

public class DeleteController extends Controller {

	private PageService pageService = GuiceUtil.getService(PageService.class);
	private PageEntityMeta pageMeta = PageEntityMeta.get();

	@Override
	public Navigation run() throws Exception {
		requestScope("pageTitle", "Smazat stránku");
		
		if (param("submit") != null) {
			Key key = asKey(pageMeta.key);
			pageService.delete(key);
			Messages.setSessionMessage("Stránka byla smazána.");

			return redirect("/admin/page/");
		}

		Key key = asKey(pageMeta.key);
		if (key == null) {
			Messages.setSessionMessage("Chybný parametr.", Message.ERROR);
			return redirect("/admin/page/");
		}
		PageEntity pageEntity = pageService.getPage(key);
		requestScope("entity", pageEntity);
		
//		Enumeration names = request.getAttributeNames();
//		String name = (String)names.nextElement();
//		for(; names.hasMoreElements(); name = (String) names.nextElement()) {
//			System.out.println(name +" "+ request.getAttribute(name));
//		}
		
		return forward("/cms/admin/page/delete.jsp");
	}
}
