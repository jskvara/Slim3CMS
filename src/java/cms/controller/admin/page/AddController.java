package cms.controller.admin.page;

import cms.model.meta.PageEntityMeta;
import cms.model.service.PageService;
import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.controller.validator.Validators;
import org.slim3.util.RequestMap;

public class AddController extends Controller {

	private PageService pageService = new PageService();
	private PageEntityMeta pageMeta = PageEntityMeta.get();

	@Override
	public Navigation run() throws Exception {
		requestScope("title", "Přidat stránku");
		if (!validate()) {
			return forward("/cms/admin/page/add.jsp");
		}
		pageService.insert(new RequestMap(request));
		
		return redirect("/cms/admin/page/");
	}

	protected boolean validate() {
		Validators v = new Validators(request);
		v.add(pageMeta.url, v.required(), v.maxlength(255));
		return v.validate();
	}
}
