package cms.model.dao;

import cms.model.model.TemplateEntity;
import cms.model.meta.TemplateMeta;
import com.google.appengine.api.datastore.Key;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.slim3.datastore.Datastore;

public class TemplateDAO {
	private TemplateMeta templateMeta = new TemplateMeta();

	public List<TemplateEntity> getAll() {
		// List<TemplateEntity> templateEntities = Datastore.query(templateMeta).sort(pageMeta.position.desc).asList();
		List<TemplateEntity> templateEntities = new ArrayList<TemplateEntity>();
		return templateEntities;
	}

	public TemplateEntity get(Key key) {
		TemplateEntity templateEntity = Datastore.get(TemplateEntity.class, key);
		return templateEntity;
	}

	public void insert(TemplateEntity templateEntity) {
		Datastore.put(templateEntity);
	}

	public void edit(TemplateEntity templateEntity) {
		Datastore.put(templateEntity);
	}

	public void delete(Key key) {
		Datastore.delete(key);
	}
}
