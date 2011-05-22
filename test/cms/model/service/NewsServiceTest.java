package cms.model.service;

import cms.model.meta.NewsEntityMeta;
import java.util.Date;
import cms.model.model.NewsEntity;
import com.google.appengine.api.datastore.Key;
import cms.util.GuiceUtil;
import org.slim3.datastore.EntityNotFoundRuntimeException;
import java.util.HashMap;
import java.util.Map;
import org.slim3.datastore.Datastore;
import org.junit.Test;
import org.slim3.tester.AppEngineTestCase;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class NewsServiceTest extends AppEngineTestCase {

	private NewsService service = GuiceUtil.getService(NewsService.class);
	private NewsEntityMeta meta = NewsEntityMeta.get();

	@Test
	public void testGetAllNews() {
		int count = Datastore.query(NewsEntity.class).count();
		
		assertThat(service.getAllNews().size(), is(count));
	}

	@Test
	public void testGetHomepageNews() {
		int count = Datastore.query(NewsEntity.class)
				.filter(meta.visible.equal(Boolean.TRUE))
				.filter(meta.created.lessThanOrEqual(new Date()))
				.sort(meta.created.desc)
				.count();

		assertThat(service.getHomepageNews().size(), is(count));
	}

	@Test
	public void testGetNews() {
		NewsEntity news = new NewsEntity();
		Datastore.put(news);
		
		assertThat(service.getNews(news.getKey()), is(notNullValue()));
		Datastore.delete(news.getKey());
	}

	@Test
	public void testGetNewsWhenModelIsNotFound() {
		Key newsKey = Datastore.createKey(NewsEntity.class, 1);
		NewsEntity noNews = service.getNews(newsKey);
		
		assertThat(noNews, is(nullValue()));
	}

	@Test
    public void testInsert() {
		Map<String, Object> input = new HashMap<String, Object>();
		input.put("title", "test-title-3");
		NewsEntity newsEntity = null;

		try {
			newsEntity = service.insert(input);
			NewsEntity stored = Datastore.get(NewsEntity.class, newsEntity.getKey());

			assertThat(stored.getTitle(), is("test-title-3"));
		} catch(ServiceException e) {
			fail(e.getErrors().toString());
		} finally {
			if (newsEntity != null) {
				Datastore.delete(newsEntity.getKey());
			}
		}
	}

	@Test
	public void testEdit() {
		NewsEntity news = new NewsEntity();
		news.setTitle("test-title-4");
		Datastore.put(news);

		Map<String, Object> input = new HashMap<String, Object>();
		input.put("key", news.getKey());
		input.put("version", news.getVersion());
		input.put("title", "test-title-5");
		NewsEntity updated = null;
		try {
			updated = service.edit(input);

			assertThat(updated.getTitle(), is("test-title-5"));
		} catch (ServiceException e) {
			fail();
		} finally {
			Datastore.delete(news.getKey());
		}
	}

	@Test
	public void testDelete() {
		NewsEntity news = new NewsEntity();
		Datastore.put(news);
		try {
			service.delete(news.getKey(), news.getVersion());
		} catch (ServiceException e) {
			fail();
		}
	}

	@Test(expected=EntityNotFoundRuntimeException.class)
	public void testDeleteWhenModelIsNotFound() {
		try {
			service.delete(Datastore.createKey(NewsEntity.class, 1), 1L);
		} catch (ServiceException ex) {
			fail();
		}
	}

	@Test
	public void testDeleteWhenOptimisticLockFailed() {
		NewsEntity news = new NewsEntity();
		Datastore.put(news);
		try {
			service.delete(news.getKey(), news.getVersion() + 1);
		} catch (ServiceException ex) {
			if (!ex.getMessage().contains("upravuje")) {
				fail();
			}
		} finally {
			Datastore.delete(news.getKey());
		}
	}
}