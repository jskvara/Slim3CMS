package cms.model.service;

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

	private PageService service = new PageService();

	@Test
	public void test() {
		assertThat(service, is(notNullValue()));
	}

	@Test
	public void get() {
		PageEntity page = new PageEntity();
		Datastore.put(page);
		assertThat(service.getPage(page.getKey()), is(notNullValue()));
	}

	@Test(expected=EntityNotFoundRuntimeException.class)
	public void getWhenModelIsNotFound() {
		service.getPage(Datastore.createKey(PageEntity.class, 1));
	}

//	@Test(expected=ConcurrentModificationException.class)
//	public void getWhenOptimisticLockFailed() {
//		PageEntity page = new PageEntity();
//		Datastore.put(page);
//		service.getPage(page.getKey(), page.getVersion() + 1);
//	}

	@Test
	public void getAll() {
		int count = Datastore.query(PageEntity.class).count();
		PageEntity page = new PageEntity();
		Datastore.put(page);
		assertThat(service.getAllPages().size(), is(count + 1));
	}

//	@Test
//	public void insert() throws Exception {
//		PageEntity page = new PageEntity();
//		service.insert(page);
//		assertThat(page.getKey(), is(notNullValue()));
//	}

	@Test
    public void insert() {
		Map<String, Object> input = new HashMap<String, Object>();
		input.put("url", "url");
		try {
			PageEntity pageEntity = service.insert(input);
			PageEntity stored = Datastore.get(PageEntity.class, pageEntity.getKey());
			assertThat(stored.getUrl(), is("url"));
		} catch(ServiceException e) {
			fail();
		}
	}

	@Test
	public void update() {
		PageEntity page = new PageEntity();
		page.setUrl("url2");
		Datastore.put(page);
		Map<String, Object> input = new HashMap<String, Object>();
		input.put("key", page.getKey());
		input.put("url", "url3");
		PageEntity updated;
		try {
			updated = service.edit(input);
			assertThat(updated.getUrl(), is("url3"));
		} catch (ServiceException ex) {
			fail();
		}
	}

	@Test
	public void delete() {
		PageEntity page = new PageEntity();
		Datastore.put(page);
		try {
			service.delete(page.getKey(), page.getVersion());
		} catch (ServiceException e) {
			fail();
		}
	}

	@Test(expected=EntityNotFoundRuntimeException.class)
	public void deleteWhenModelIsNotFound() {
		try {
			service.delete(Datastore.createKey(PageEntity.class, 1), 1L);
		} catch (ServiceException ex) {
			fail();
		}
	}

//	@Test(expected=ConcurrentModificationException.class)
//	public void deleteWhenOptimisticLockFailed() {
//		PageEntity page = new PageEntity();
//		Datastore.put(page);
//		service.delete(page.getKey(), page.getVersion() + 1);
//	}
}