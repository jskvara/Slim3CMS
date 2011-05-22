package cms.model.service;

import cms.model.model.AuthorEntity;
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

public class AuthorServiceTest extends AppEngineTestCase {

	private AuthorService service = GuiceUtil.getService(AuthorService.class);

	@Test
	public void testGetAllAuthors() {
		int count = Datastore.query(AuthorEntity.class).count();
		
		assertThat(service.getAllAuthors().size(), is(count));
	}

	@Test
	public void testGetAuthor() {
		AuthorEntity author = new AuthorEntity();
		Datastore.put(author);
		
		assertThat(service.getAuthor(author.getKey()), is(notNullValue()));
		Datastore.delete(author.getKey());
	}

	@Test
	public void testGetAuthorWhenModelIsNotFound() {
		Key authorKey = Datastore.createKey(AuthorEntity.class, 1);
		AuthorEntity noAuthor = service.getAuthor(authorKey);
		
		assertThat(noAuthor, is(nullValue()));
	}

	@Test
	public void testGetAuthorByEmail() {
		AuthorEntity author = new AuthorEntity();
		author.setEmail("test-email-1");
		Datastore.put(author);

		assertThat(service.getAuthorByEmail(author.getEmail()), is(notNullValue()));
		Datastore.delete(author.getKey());
	}

	@Test
	public void testIsAuthor() {
		AuthorEntity author = new AuthorEntity();
		author.setEmail("test-email-2");
		Datastore.put(author);

		assertThat(service.isAuthor(author.getEmail()), is(true));
		Datastore.delete(author.getKey());
		assertThat(service.isAuthor(author.getEmail()), is(false));
	}

	@Test
    public void testInsert() {
		Map<String, Object> input = new HashMap<String, Object>();
		input.put("email", "test-email-3");
		AuthorEntity authorEntity = null;

		try {
			authorEntity = service.insert(input);
			AuthorEntity stored = Datastore.get(AuthorEntity.class, authorEntity.getKey());

			assertThat(stored.getEmail(), is("test-email-3"));
		} catch(ServiceException e) {
			fail(e.getErrors().toString());
		} finally {
			if (authorEntity != null) {
				Datastore.delete(authorEntity.getKey());
			}
		}
	}

	@Test
	public void testEdit() {
		AuthorEntity author = new AuthorEntity();
		author.setEmail("test-email-4");
		Datastore.put(author);

		Map<String, Object> input = new HashMap<String, Object>();
		input.put("key", author.getKey());
		input.put("version", author.getVersion());
		input.put("email", "test-email-5");
		AuthorEntity updated = null;
		try {
			updated = service.edit(input);

			assertThat(updated.getEmail(), is("test-email-5"));
		} catch (ServiceException e) {
			fail();
		} finally {
			Datastore.delete(author.getKey());
		}
	}

	@Test
	public void testDelete() {
		AuthorEntity author = new AuthorEntity();
		Datastore.put(author);
		try {
			service.delete(author.getKey(), author.getVersion());
		} catch (ServiceException e) {
			fail();
		}
	}

	@Test(expected=EntityNotFoundRuntimeException.class)
	public void testDeleteWhenModelIsNotFound() {
		try {
			service.delete(Datastore.createKey(AuthorEntity.class, 1), 1L);
		} catch (ServiceException ex) {
			fail();
		}
	}

	@Test
	public void testDeleteWhenOptimisticLockFailed() {
		AuthorEntity author = new AuthorEntity();
		Datastore.put(author);
		try {
			service.delete(author.getKey(), author.getVersion() + 1);
		} catch (ServiceException ex) {
			if (!ex.getMessage().contains("upravuje")) {
				fail();
			}
		} finally {
			Datastore.delete(author.getKey());
		}
	}
}