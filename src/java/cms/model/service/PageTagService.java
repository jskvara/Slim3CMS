package cms.model.service;

import cms.model.dao.PageTagDAO;
import cms.model.model.PageEntity;
import cms.model.model.PageTagEntity;
import cms.model.model.TagEntity;
import cms.util.GuiceUtil;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.inject.Inject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PageTagService implements Service {
	@Inject
	private PageTagDAO pageTagDAO;
	private TagService tagService = GuiceUtil.getService(TagService.class);
	private PageService pageService = GuiceUtil.getService(PageService.class);

	public PageTagEntity insert(PageEntity pageEntity, String tagName) throws ServiceException {
		TagEntity tagEntity = tagService.getTagByName(tagName);
		if (tagEntity == null) {
			throw new ServiceException("tagArray", "Tento štítek neexistuje.");
		}

		PageTagEntity pageTagEntity = new PageTagEntity();
		pageTagEntity.getPageRef().setModel(pageEntity);
		pageTagEntity.getTagRef().setModel(tagEntity);
		
		return pageTagDAO.insert(pageTagEntity);
	}

	public void edit(Map<String, Object> input) throws ServiceException {
		
		Key pageKey = KeyFactory.stringToKey((String) input.get("key"));
		PageEntity pageEntity = pageService.getPage(pageKey);
		List<PageTagEntity> pageTagEntities = pageTagDAO.getByPage(pageKey);
		Map<String,PageTagEntity> oldPageTagEntities = new HashMap<String,PageTagEntity>();
		for (PageTagEntity pageTagEntity : pageTagEntities) {
			TagEntity tagEntity = pageTagEntity.getTagRef().getModel();
			oldPageTagEntities.put(tagEntity.getName(), pageTagEntity);
		}

		String[] tagArray = (String[]) input.get("tagArray");
		for(String tagName : tagArray) {
			// keep
			if (oldPageTagEntities.containsKey(tagName)) {
				oldPageTagEntities.remove(tagName);
				continue;
			}

			// add
			insert(pageEntity, tagName);
		}
		
		// delete
		pageTagDAO.deleteAll(oldPageTagEntities.values());
	}
}