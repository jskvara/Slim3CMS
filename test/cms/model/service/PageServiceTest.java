package cms.model.service;

import com.google.appengine.api.datastore.Key;
import cms.util.GuiceUtil;
import cms.model.model.PageEntity;
import org.slim3.datastore.EntityNotFoundRuntimeException;
import java.util.HashMap;
import java.util.Map;
import org.slim3.datastore.Datastore;
import org.junit.Test;
import org.slim3.tester.AppEngineTestCase;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class PageServiceTest extends AppEngineTestCase {

	private PageService service = GuiceUtil.getService(PageService.class);

	@Test
	public void testGetAllPages() {
		int count = Datastore.query(PageEntity.class).count();
		
		assertThat(service.getAllPages().size(), is(count));
	}

	@Test
	public void testGetPage() {
		PageEntity page = new PageEntity();
		Datastore.put(page);
		
		assertThat(service.getPage(page.getKey()), is(notNullValue()));
		Datastore.delete(page.getKey());
	}

	@Test
	public void testGetPageWhenModelIsNotFound() {
		Key pageKey = Datastore.createKey(PageEntity.class, 1);
		PageEntity noPage = service.getPage(pageKey);
		
		assertThat(noPage, is(nullValue()));
	}

	@Test
	public void testGetPageByUrl() {
		PageEntity page = new PageEntity();
		page.setUrl("test-url-1");
		Datastore.put(page);

		assertThat(service.getPageByUrl(page.getUrl()), is(notNullValue()));
		Datastore.delete(page.getKey());
	}

	@Test
	public void testGetVisiblePageByUrl() {
		PageEntity page = new PageEntity();
		page.setVisible(true);
		page.setUrl("test-url-2");
		Datastore.put(page);

		assertThat(service.getVisiblePageByUrl(page.getUrl()), is(notNullValue()));
		Datastore.delete(page.getKey());
	}

	@Test
    public void testInsert() {
		Map<String, Object> input = new HashMap<String, Object>();
		input.put("url", "test-url-3");
		PageEntity pageEntity = null;

		try {
			pageEntity = service.insert(input);
			PageEntity stored = Datastore.get(PageEntity.class, pageEntity.getKey());

			assertThat(stored.getUrl(), is("test-url-3"));
		} catch(ServiceException e) {
			fail(e.getErrors().toString());
		} finally {
			if (pageEntity != null) {
				Datastore.delete(pageEntity.getKey());
			}
		}
	}

	@Test
	public void testEdit() {
		PageEntity page = new PageEntity();
		page.setUrl("test-url-4");
		Datastore.put(page);

		Map<String, Object> input = new HashMap<String, Object>();
		input.put("key", page.getKey());
		input.put("version", page.getVersion());
		input.put("url", "test-url-5");
		PageEntity updated = null;
		try {
			updated = service.edit(input);

			assertThat(updated.getUrl(), is("test-url-5"));
		} catch (ServiceException e) {
			fail();
		} finally {
			Datastore.delete(page.getKey());
		}
	}

	@Test
	public void testDelete() {
		PageEntity page = new PageEntity();
		Datastore.put(page);
		try {
			service.delete(page.getKey(), page.getVersion());
		} catch (ServiceException e) {
			fail();
		}
	}

	@Test(expected=EntityNotFoundRuntimeException.class)
	public void testDeleteWhenModelIsNotFound() {
		try {
			service.delete(Datastore.createKey(PageEntity.class, 1), 1L);
		} catch (ServiceException ex) {
			fail();
		}
	}

	@Test
	public void testDeleteWhenOptimisticLockFailed() {
		PageEntity page = new PageEntity();
		Datastore.put(page);
		try {
			service.delete(page.getKey(), page.getVersion() + 1);
		} catch (ServiceException ex) {
			if (!ex.getMessage().contains("upravuje")) {
				fail();
			}
		} finally {
			Datastore.delete(page.getKey());
		}
	}
}