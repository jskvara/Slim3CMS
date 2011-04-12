package cms.model.dao;

import cms.model.model.TemplateEntity;
import cms.model.meta.TemplateEntityMeta;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Transaction;
import java.util.List;
import org.slim3.datastore.Datastore;

public class TemplateDAO {
	private TemplateEntityMeta templateMeta = new TemplateEntityMeta();

	public List<TemplateEntity> getAll() {
		List<TemplateEntity> templateEntities = Datastore.query(templateMeta).sort(templateMeta.name.desc).asList();
		return templateEntities;
	}

	public TemplateEntity get(Key key) {
		TemplateEntity templateEntity = Datastore.get(TemplateEntity.class, key);
		return templateEntity;
	}

	public TemplateEntity getByName(String name) {
		TemplateEntity templateEntity = Datastore.query(templateMeta).filter(templateMeta.name.equal(name)).asSingle();
		return templateEntity;
	}

	public TemplateEntity insert(TemplateEntity templateEntity) {
		Transaction tx = Datastore.beginTransaction();
		Datastore.put(templateEntity);
		tx.commit();

		return templateEntity;
	}

	public TemplateEntity edit(TemplateEntity templateEntity) {
		Transaction tx = Datastore.beginTransaction();
		Datastore.put(templateEntity);
		tx.commit();

		return templateEntity;
	}

	public void delete(Key key) {
		Transaction tx = Datastore.beginTransaction();
		TemplateEntity tag = Datastore.get(tx, templateMeta, key);
		Datastore.delete(tx, tag.getKey());
		tx.commit();
	}
}
