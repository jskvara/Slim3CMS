package cms.controller.admin.page;

import cms.model.meta.PageEntityMeta;
import cms.model.model.PageEntity;
import cms.model.service.PageService;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.controller.validator.Validators;
import org.slim3.util.BeanUtil;
import org.slim3.util.RequestMap;

public class EditController extends Controller {

	private PageService pageService = new PageService();
	private PageEntityMeta pageMeta = PageEntityMeta.get();

	@Override
	public Navigation run() throws Exception {
		requestScope("title", "Upravit stránku");

		if (request.getParameter("submit") != null) {
			if (!validate()) {
				PageEntity pageEntity = pageService.getPage(asKey(pageMeta.key));
				BeanUtil.copy(pageEntity, request);
				return forward("/cms/admin/page/edit.jsp");
			}

			RequestMap requestMap = new RequestMap(request);
			requestMap.put("key", asKey(pageMeta.key));
			//requestMap.put("version", pageMeta.version);
			pageService.edit(requestMap);

			// sessions
			HttpSession session = request.getSession();
			List<String> messages = (List<String>)request.getAttribute("messages");
			if (messages == null) {
				messages = new LinkedList();
			}
			messages.add("Stránka byla upravena.");
			session.setAttribute("messages", messages);
			// --sessions

			return redirect("/admin/page/");
		}

		PageEntity pageEntity = pageService.getPage(asKey(pageMeta.key));
		BeanUtil.copy(pageEntity, request);
		return forward("/cms/admin/page/edit.jsp");
	}

	protected boolean validate() {
		Validators v = new Validators(request);
		v.add(pageMeta.url, v.required(), v.maxlength(255));
		return v.validate();
	}
}
