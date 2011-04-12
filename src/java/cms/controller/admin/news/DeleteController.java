package cms.controller.admin.news;

import cms.model.meta.NewsEntityMeta;
import cms.model.model.NewsEntity;
import cms.model.service.NewsService;
import cms.util.Message;
import cms.util.Messages;
import com.google.appengine.api.datastore.Key;
import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

public class DeleteController extends Controller {

	private NewsService newsService = new NewsService();
	private NewsEntityMeta newsMeta = NewsEntityMeta.get();

	@Override
	public Navigation run() throws Exception {
		requestScope("pageTitle", "Smazat novinku");
		
		if (param("submit") != null) {
			Key key = asKey(newsMeta.key);
			newsService.delete(key);
			Messages.setSessionMessage("Novinka byla smazána.");

			return redirect("/admin/news/");
		}

		Key key = asKey(newsMeta.key);
		if (key == null) {
			Messages.setSessionMessage("Chybný parametr.", Message.ERROR);
			return redirect("/admin/news/");
		}
		NewsEntity newsEntity = newsService.getNews(key);
		requestScope("entity", newsEntity);
		
		return forward("/cms/admin/news/delete.jsp");
	}
}
