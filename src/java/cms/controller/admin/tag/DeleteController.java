package cms.controller.admin.tag;

import cms.model.meta.TagEntityMeta;
import cms.model.model.TagEntity;
import cms.model.service.TagService;
import cms.util.Message;
import cms.util.Messages;
import com.google.appengine.api.datastore.Key;
import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

public class DeleteController extends Controller {

	private TagService tagService = new TagService();
	private TagEntityMeta tagMeta = TagEntityMeta.get();

	@Override
	public Navigation run() throws Exception {
		requestScope("pageTitle", "Smazat štítek");
		
		if (param("submit") != null) {
			Key key = asKey(tagMeta.key);
			tagService.delete(key);
			Messages.setSessionMessage("Štítek byl smazán.");

			return redirect("/admin/tag/");
		}

		Key key = asKey(tagMeta.key);
		if (key == null) {
			Messages.setSessionMessage("Chybný parametr.", Message.ERROR);
			return redirect("/admin/tag/");
		}
		TagEntity tagEntity = tagService.getTag(key);
		requestScope("entity", tagEntity);
		
		return forward("/cms/admin/tag/delete.jsp");
	}
}
