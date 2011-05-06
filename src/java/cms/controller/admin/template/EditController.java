package cms.controller.admin.template;

import cms.controller.admin.AdminController;
import cms.model.meta.TemplateEntityMeta;
import cms.model.model.TemplateEntity;
import cms.model.model.dto.TemplateDTO;
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

	private TemplateService templateService = GuiceUtil.getService(TemplateService.class);
	private TemplateEntityMeta templateMeta = TemplateEntityMeta.get();

	@Override
	public Navigation run() throws Exception {
		requestScope("pageTitle", "Upravit šablonu");

		if (param("submit") != null) {
			try {
				templateService.edit(new RequestMap(request));
			} catch(ServiceException e) {
				if (e.getErrors() != null) {
					requestScope("errors", e.getErrors());
				} else {
					Messages.setRequestMessage(e.getMessage(), Message.ERROR);
				}
				
				return forward("/cms/admin/template/edit.jsp");
			}
			Messages.setSessionMessage("Šablona byla upravena.");
			return redirect("/admin/template/");
		}

		Key key = asKey(templateMeta.key);
		if (key == null) {
			Messages.setSessionMessage("Chybný parametr,", Message.ERROR);
			return redirect("/admin/template/");
		}
		TemplateEntity templateEntity = templateService.getTemplate(key);
		TemplateDTO templateDTO = new TemplateDTO(templateEntity);
		BeanUtil.copy(templateDTO, request);
		
		return forward("/cms/admin/template/edit.jsp");
	}
}
