package cms.controller.admin.news;

import cms.controller.admin.AdminController;
import cms.model.meta.NewsEntityMeta;
import cms.model.model.NewsEntity;
import cms.model.model.dto.NewsDTO;
import cms.model.service.NewsService;
import cms.model.service.ServiceException;
import cms.util.GuiceUtil;
import cms.util.Message;
import cms.util.Messages;
import com.google.appengine.api.datastore.Key;
import org.slim3.controller.Navigation;
import org.slim3.util.BeanUtil;
import org.slim3.util.RequestMap;

public class EditController extends AdminController {

	private NewsService newsService = GuiceUtil.getService(NewsService.class);
	private NewsEntityMeta newsMeta = NewsEntityMeta.get();

	@Override
	public Navigation run() throws Exception {
		requestScope("pageTitle", "Upravit novinku");

		if (param("submit") != null) {
			try {
				newsService.edit(new RequestMap(request));
			} catch (ServiceException e) {
				if (e.getErrors() != null) {
					requestScope("errors", e.getErrors());
				} else {
					Messages.setRequestMessage(e.getMessage(), Message.ERROR);
				}

				return forward("/cms/admin/news/edit.jsp");
			}
			Messages.setSessionMessage("Novinka byla upravena.");
			return redirect("/admin/news/");
		}

		Key key = asKey(newsMeta.key);
		if (key == null) {
			Messages.setSessionMessage("Chybný parametr.", Message.ERROR);
			return redirect("/admin/news/");
		}
		NewsEntity newsEntity = newsService.getNews(key);
		NewsDTO newsDTO = new NewsDTO(newsEntity);
		BeanUtil.copy(newsDTO, request);

		return forward("/cms/admin/news/edit.jsp");
	}
}
