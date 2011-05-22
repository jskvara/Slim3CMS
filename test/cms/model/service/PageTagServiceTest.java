package cms.model.service;

import cms.model.model.PageEntity;
import cms.model.model.PageTagEntity;
import cms.model.model.TagEntity;
import cms.util.GuiceUtil;
import com.google.appengine.api.datastore.KeyFactory;
import java.util.HashMap;
import java.util.Map;
import org.slim3.datastore.Datastore;
import org.junit.Test;
import org.slim3.tester.AppEngineTestCase;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class PageTagServiceTest extends AppEngineTestCase {

	private PageTagService service = GuiceUtil.getService(PageTagService.class);

	@Test
    public void testInsertAndEdit() {
		PageEntity pageEntity = new PageEntity();
		Datastore.put(pageEntity);

		TagEntity tagEntity = new TagEntity();
		tagEntity.setName("test-pageTag-name-1");
		Datastore.put(tagEntity);

		PageTagEntity stored = null;
		PageTagEntity updated = null;

		try {
			stored = service.insert(pageEntity, tagEntity.getName());

			assertThat(stored.getPageRef().getKey(), is(pageEntity.getKey()));
			assertThat(stored.getTagRef().getKey(), is(tagEntity.getKey()));

			Map<String, Object> input = new HashMap<String, Object>();
			input.put("key", KeyFactory.keyToString(pageEntity.getKey()));
			input.put("tagArray", new String[0]);

			service.edit(input);

			assertThat(pageEntity.getPageTagListRef().getModelList().size(), is(0));
		} catch(ServiceException e) {
			fail(e.getErrors().toString());
		} finally {
			if (pageEntity != null) {
				Datastore.delete(pageEntity.getKey());
			}
			if (tagEntity != null) {
				Datastore.delete(tagEntity.getKey());
			}
		}
	}
}