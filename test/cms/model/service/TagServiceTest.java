package cms.model.service;

import cms.model.model.TagEntity;
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

public class TagServiceTest extends AppEngineTestCase {

	private TagService service = GuiceUtil.getService(TagService.class);

	@Test
	public void testGetAllTags() {
		int count = Datastore.query(TagEntity.class).count();
		
		assertThat(service.getAllTags().size(), is(count));
	}

	@Test
	public void testGetTag() {
		TagEntity tag = new TagEntity();
		Datastore.put(tag);
		
		assertThat(service.getTag(tag.getKey()), is(notNullValue()));
		Datastore.delete(tag.getKey());
	}

	@Test
	public void testGetTagWhenModelIsNotFound() {
		Key tagKey = Datastore.createKey(TagEntity.class, 1);
		TagEntity noTag = service.getTag(tagKey);
		
		assertThat(noTag, is(nullValue()));
	}

	@Test
	public void testGetTagByName() {
		TagEntity tag = new TagEntity();
		tag.setName("test-name-1");
		Datastore.put(tag);

		assertThat(service.getTagByName(tag.getName()), is(notNullValue()));
		Datastore.delete(tag.getKey());
	}

	@Test
    public void testInsert() {
		Map<String, Object> input = new HashMap<String, Object>();
		input.put("name", "test-name-3");
		TagEntity tagEntity = null;

		try {
			tagEntity = service.insert(input);
			TagEntity stored = Datastore.get(TagEntity.class, tagEntity.getKey());

			assertThat(stored.getName(), is("test-name-3"));
		} catch(ServiceException e) {
			fail(e.getErrors().toString());
		} finally {
			if (tagEntity != null) {
				Datastore.delete(tagEntity.getKey());
			}
		}
	}

	@Test
	public void testEdit() {
		TagEntity tag = new TagEntity();
		tag.setName("test-name-4");
		Datastore.put(tag);

		Map<String, Object> input = new HashMap<String, Object>();
		input.put("key", tag.getKey());
		input.put("version", tag.getVersion());
		input.put("name", "test-name-5");
		TagEntity updated = null;
		try {
			updated = service.edit(input);

			assertThat(updated.getName(), is("test-name-5"));
		} catch (ServiceException e) {
			fail();
		} finally {
			Datastore.delete(tag.getKey());
		}
	}

	@Test
	public void testDelete() {
		TagEntity tag = new TagEntity();
		Datastore.put(tag);
		try {
			service.delete(tag.getKey(), tag.getVersion());
		} catch (ServiceException e) {
			fail();
		}
	}

	@Test(expected=EntityNotFoundRuntimeException.class)
	public void testDeleteWhenModelIsNotFound() {
		try {
			service.delete(Datastore.createKey(TagEntity.class, 1), 1L);
		} catch (ServiceException ex) {
			fail();
		}
	}

	@Test
	public void testDeleteWhenOptimisticLockFailed() {
		TagEntity tag = new TagEntity();
		Datastore.put(tag);
		try {
			service.delete(tag.getKey(), tag.getVersion() + 1);
		} catch (ServiceException ex) {
			if (!ex.getMessage().contains("upravuje")) {
				fail();
			}
		} finally {
			Datastore.delete(tag.getKey());
		}
	}
}