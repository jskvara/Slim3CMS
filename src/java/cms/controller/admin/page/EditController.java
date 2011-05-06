package cms.controller.admin.page;

import cms.controller.admin.AdminController;
import cms.model.meta.PageEntityMeta;
import cms.model.model.PageEntity;
import cms.model.model.dto.PageDTO;
import cms.model.service.PageService;
import cms.model.service.ServiceException;
import cms.model.service.TemplateService;
import cms.util.GuiceUtil;
import cms.util.Message;
import cms.util.Messages;
import com.google.appengine.api.datastore.Key;
import org.slim3.controller.Navigation;
import org.slim3.util.BeanUtil;
import org.slim3.util.RequestMap;

public class EditController extends AdminController {

	private PageService pageService = GuiceUtil.getService(PageService.class);
	private TemplateService templateService = GuiceUtil.getService(TemplateService.class);
	private PageEntityMeta pageMeta = PageEntityMeta.get();

	@Override
	public Navigation run() throws Exception {
		requestScope("pageTitle", "Upravit stránku");

		if (param("submit") != null) {
			try {
				pageService.edit(new RequestMap(request));
			} catch (ServiceException e) {
				if (e.getErrors() != null) {
					requestScope("errors", e.getErrors());
				} else {
					Messages.setRequestMessage(e.getMessage(), Message.ERROR);
				}
				requestScope("templates", templateService.getAllTemplates());

				return forward("/cms/admin/page/edit.jsp");
			}
			Messages.setSessionMessage("Stránka byla upravena.");
			return redirect("/admin/page/");
		}

		Key key = asKey(pageMeta.key);
		if (key == null) {
			Messages.setSessionMessage("Chybný parametr.", Message.ERROR);
			return redirect("/admin/page/");
		}
		PageEntity pageEntity = pageService.getPage(key);
		PageDTO pageDTO = new PageDTO(pageEntity);
		BeanUtil.copy(pageDTO, request);
		requestScope("templates", templateService.getAllTemplates());
		requestScope("templateName", pageDTO.getTemplateName());

		return forward("/cms/admin/page/edit.jsp");
	}
}
