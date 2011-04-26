package cms.controller.admin.template;

import cms.controller.admin.AdminController;
import cms.model.meta.TemplateEntityMeta;
import cms.model.model.TemplateEntity;
import cms.model.model.dto.TemplateDTO;
import cms.model.service.TemplateService;
import cms.util.GuiceUtil;
import cms.util.Message;
import cms.util.Messages;
import com.google.appengine.api.datastore.Key;
import org.slim3.controller.Navigation;

public class DeleteController extends AdminController {

	private TemplateService templateService = GuiceUtil.getService(TemplateService.class);
	private TemplateEntityMeta templateMeta = TemplateEntityMeta.get();

	@Override
	public Navigation run() throws Exception {
		requestScope("pageTitle", "Smazat šablonu");
		
		if (param("submit") != null) {
			Key key = asKey(templateMeta.key);
			templateService.delete(key);
			Messages.setSessionMessage("Šablona byla smazána.");

			return redirect("/admin/template/");
		}

		Key key = asKey(templateMeta.key);
		if (key == null) {
			Messages.setSessionMessage("Chybný parametr.", Message.ERROR);
			return redirect("/admin/template/");
		}
		TemplateEntity templateEntity = templateService.getTemplate(key);
		TemplateDTO templateDTO = new TemplateDTO(templateEntity);
		requestScope("entity", templateDTO);
		
		return forward("/cms/admin/template/delete.jsp");
	}
}
