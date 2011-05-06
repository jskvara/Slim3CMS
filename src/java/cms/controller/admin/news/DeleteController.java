package cms.controller.admin.news;

import cms.controller.admin.AdminController;
import cms.model.meta.NewsEntityMeta;
import cms.model.model.NewsEntity;
import cms.model.service.NewsService;
import cms.model.service.ServiceException;
import cms.util.GuiceUtil;
import cms.util.Message;
import cms.util.Messages;
import com.google.appengine.api.datastore.Key;
import org.slim3.controller.Navigation;

public class DeleteController extends AdminController {

	private NewsService newsService = GuiceUtil.getService(NewsService.class);
	private NewsEntityMeta newsMeta = NewsEntityMeta.get();

	@Override
	public Navigation run() throws Exception {
		requestScope("pageTitle", "Smazat novinku");

		if (param("submit") != null) {
			Key key = asKey(newsMeta.key);
			Long version = asLong(newsMeta.version);
			if (key == null || version == null) {
				Messages.setSessionMessage("Novinka neexistuje.", Message.ERROR);
				return redirect("/admin/news/");
			}
			try {
				newsService.delete(key, version);
			} catch (ServiceException e) {
				Messages.setSessionMessage(e.getMessage(), Message.ERROR);

				return redirect("/admin/news/");
			}
			Messages.setSessionMessage("Novinka byla smazána.");

			return redirect("/admin/news/");
		}

		Key key = asKey(newsMeta.key);
		if (key == null) {
			Messages.setSessionMessage("Novinka neexistuje.", Message.ERROR);
			return redirect("/admin/news/");
		}
		NewsEntity newsEntity = newsService.getNews(key);
		requestScope("entity", newsEntity);
		requestScope("version", newsEntity.getVersion());

		return forward("/cms/admin/news/delete.jsp");
	}
}
