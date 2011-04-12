package cms.controller.admin.template;

import cms.model.meta.TemplateEntityMeta;
import cms.model.model.TemplateEntity;
import cms.model.service.TemplateService;
import cms.util.Message;
import cms.util.Messages;
import com.google.appengine.api.datastore.Key;
import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

public class DeleteController extends Controller {

	private TemplateService templateService = new TemplateService();
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
		requestScope("entity", templateEntity);
		
		return forward("/cms/admin/template/delete.jsp");
	}
}
