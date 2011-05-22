package cms.model.service;

import cms.model.model.TemplateEntity;
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

public class TemplateServiceTest extends AppEngineTestCase {

	private TemplateService service = GuiceUtil.getService(TemplateService.class);

	@Test
	public void testGetAllTemplates() {
		int count = Datastore.query(TemplateEntity.class).count();
		
		assertThat(service.getAllTemplates().size(), is(count));
	}

	@Test
	public void testGetTemplate() {
		TemplateEntity template = new TemplateEntity();
		Datastore.put(template);
		
		assertThat(service.getTemplate(template.getKey()), is(notNullValue()));
		Datastore.delete(template.getKey());
	}

	@Test
	public void testGetTemplateWhenModelIsNotFound() {
		Key templateKey = Datastore.createKey(TemplateEntity.class, 1);
		TemplateEntity noTemplate = service.getTemplate(templateKey);
		
		assertThat(noTemplate, is(nullValue()));
	}

	@Test
	public void testGetTemplateByName() {
		TemplateEntity template = new TemplateEntity();
		template.setName("test-name-1");
		Datastore.put(template);

		assertThat(service.getTemplateByName(template.getName()), is(notNullValue()));
		Datastore.delete(template.getKey());
	}

	@Test
    public void testInsert() {
		Map<String, Object> input = new HashMap<String, Object>();
		input.put("name", "test-name-3");
		TemplateEntity templateEntity = null;

		try {
			templateEntity = service.insert(input);
			TemplateEntity stored = Datastore.get(TemplateEntity.class, templateEntity.getKey());

			assertThat(stored.getName(), is("test-name-3"));
		} catch(ServiceException e) {
			fail(e.getErrors().toString());
		} finally {
			if (templateEntity != null) {
				Datastore.delete(templateEntity.getKey());
			}
		}
	}

	@Test
	public void testEdit() {
		TemplateEntity template = new TemplateEntity();
		template.setName("test-name-4");
		Datastore.put(template);

		Map<String, Object> input = new HashMap<String, Object>();
		input.put("key", template.getKey());
		input.put("version", template.getVersion());
		input.put("name", "test-name-5");
		TemplateEntity updated = null;
		try {
			updated = service.edit(input);

			assertThat(updated.getName(), is("test-name-5"));
		} catch (ServiceException e) {
			fail();
		} finally {
			Datastore.delete(template.getKey());
		}
	}

	@Test
	public void testDelete() {
		TemplateEntity template = new TemplateEntity();
		Datastore.put(template);
		try {
			service.delete(template.getKey(), template.getVersion());
		} catch (ServiceException e) {
			fail();
		}
	}

	@Test(expected=EntityNotFoundRuntimeException.class)
	public void testDeleteWhenModelIsNotFound() {
		try {
			service.delete(Datastore.createKey(TemplateEntity.class, 1), 1L);
		} catch (ServiceException ex) {
			fail();
		}
	}

	@Test
	public void testDeleteWhenOptimisticLockFailed() {
		TemplateEntity template = new TemplateEntity();
		Datastore.put(template);
		try {
			service.delete(template.getKey(), template.getVersion() + 1);
		} catch (ServiceException ex) {
			if (!ex.getMessage().contains("upravuje")) {
				fail();
			}
		} finally {
			Datastore.delete(template.getKey());
		}
	}
}