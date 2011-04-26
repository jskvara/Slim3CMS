package cms.controller.admin.page;

import cms.controller.admin.AdminController;
import cms.model.meta.PageEntityMeta;
import cms.model.model.PageEntity;
import cms.model.model.dto.PageDTO;
import cms.model.service.PageService;
import cms.model.service.PageTagService;
import cms.model.service.ServiceException;
import cms.model.service.TagService;
import cms.util.GuiceUtil;
import cms.util.Message;
import cms.util.Messages;
import com.google.appengine.api.datastore.Key;
import org.slim3.controller.Navigation;
import org.slim3.util.RequestMap;

public class TagsController extends AdminController {

	private PageService pageService = GuiceUtil.getService(PageService.class);
	private PageTagService pageTagService = GuiceUtil.getService(PageTagService.class);
	private TagService tagService = GuiceUtil.getService(TagService.class);
	private PageEntityMeta pageMeta = PageEntityMeta.get();

	@Override
	public Navigation run() throws Exception {
		requestScope("pageTitle", "Upravit štítky");

		if (param("submit") != null) {
			try {
				pageTagService.edit(new RequestMap(request));
			} catch(ServiceException e) {
				request.setAttribute("errors", e.getErrors());
				requestScope("tags", tagService.getAllTags());
				
				return forward("/cms/admin/page/tags.jsp");
			}
			Messages.setSessionMessage("Štítky byly upraveny.");
			return redirect("/admin/page/");
		}

		Key key = asKey(pageMeta.key);
		if (key == null) {
			Messages.setSessionMessage("Chybný parametr.", Message.ERROR);
			return redirect("/admin/page/");
		}
		PageEntity pageEntity = pageService.getPage(key);
		PageDTO pageDTO = new PageDTO(pageEntity);
		requestScope("tags", tagService.getAllTags());
		requestScope("tagArray", pageDTO.getTags());
		
		return forward("/cms/admin/page/tags.jsp");
	}
}
