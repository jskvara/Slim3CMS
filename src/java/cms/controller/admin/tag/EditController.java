package cms.controller.admin.tag;

import cms.model.meta.TagEntityMeta;
import cms.model.model.TagEntity;
import cms.model.service.ServiceException;
import cms.model.service.TagService;
import cms.util.Message;
import cms.util.Messages;
import com.google.appengine.api.datastore.Key;
import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.util.BeanUtil;
import org.slim3.util.RequestMap;

public class EditController extends Controller {

	private TagService tagService = new TagService();
	private TagEntityMeta tagMeta = TagEntityMeta.get();

	@Override
	public Navigation run() throws Exception {
		requestScope("pageTitle", "Upravit štítek");

		if (param("submit") != null) {
			try {
				tagService.edit(new RequestMap(request));
			} catch(ServiceException e) {
				request.setAttribute("errors", e.getErrors());
				return forward("/cms/admin/tag/edit.jsp");
			}
			Messages.setSessionMessage("Štítek byl upraven.");
			return redirect("/admin/tag/");
		}

		Key key = asKey(tagMeta.key);
		if (key == null) {
			Messages.setSessionMessage("Chybný parametr,", Message.ERROR);
			return redirect("/admin/tag/");
		}
		TagEntity tagEntity = tagService.getTag(key);
		BeanUtil.copy(tagEntity, request);

		return forward("/cms/admin/tag/edit.jsp");
	}
}
