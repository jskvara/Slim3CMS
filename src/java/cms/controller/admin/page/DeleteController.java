package cms.controller.admin.page;

import cms.model.meta.PageEntityMeta;
import cms.model.model.PageEntity;
import cms.model.service.PageService;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.controller.validator.Validators;
import org.slim3.util.BeanUtil;
import org.slim3.util.RequestMap;

public class DeleteController extends Controller {

	private PageService pageService = new PageService();
	private PageEntityMeta pageMeta = PageEntityMeta.get();

	@Override
	public Navigation run() throws Exception {
		requestScope("title", "Smazat stránku");
		
		if (request.getParameter("submit") != null) {
			pageService.insert(new RequestMap(request));
			// request.setAttribute("messages", "Stránka byla přidána.");
			HttpSession session = request.getSession();
			List<String> messages = (List<String>)request.getAttribute("messages");
			if (messages == null) {
				messages = new LinkedList();
			}
			messages.add("Stránka byla přidána.");
			session.setAttribute("messages", messages);
			
			return redirect("/admin/page/");
		}

		PageEntity pageEntity = pageService.getPage(asKey(pageMeta.key));
		System.out.println(pageEntity);
		BeanUtil.copy(pageEntity, request);
		Enumeration names = request.getAttributeNames();
		for(String name = ""; names.hasMoreElements(); name = (String) names.nextElement()) {
			System.out.println(name +" "+ request.getAttribute(name));
		}
		return forward("/cms/admin/page/delete.jsp");
	}
}
