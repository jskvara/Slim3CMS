package cms.controller.admin.page;

import cms.model.meta.PageEntityMeta;
import cms.model.model.PageEntity;
import cms.model.service.PageService;
import cms.util.Messages;
import com.google.appengine.api.datastore.Key;
import java.util.Enumeration;
import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.util.BeanUtil;

public class DeleteController extends Controller {

	private PageService pageService = new PageService();
	private PageEntityMeta pageMeta = PageEntityMeta.get();

	@Override
	public Navigation run() throws Exception {
		requestScope("title", "Smazat stránku");
		
		if (request.getParameter("submit") != null) {
			Key key = asKey(pageMeta.key);
			pageService.delete(key);
			Messages.setSessionMessage("Stránka byla smazána.");

			return redirect("/admin/page/");
		}

		PageEntity pageEntity = pageService.getPage(asKey(pageMeta.key));
		requestScope("entity", pageEntity);
		BeanUtil.copy(pageEntity, request);
		
//		Enumeration names = request.getAttributeNames();
//		String name = (String)names.nextElement();
//		for(; names.hasMoreElements(); name = (String) names.nextElement()) {
//			System.out.println(name +" "+ request.getAttribute(name));
//		}
		
		return forward("/cms/admin/page/delete.jsp");
	}
}
