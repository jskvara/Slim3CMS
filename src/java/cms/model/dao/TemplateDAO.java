package cms.model.dao;

import cms.model.model.TemplateEntity;
import cms.model.meta.TemplateEntityMeta;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Transaction;
import java.util.ConcurrentModificationException;
import java.util.List;
import org.slim3.datastore.Datastore;
import org.slim3.datastore.EntityNotFoundRuntimeException;

public class TemplateDAO {
	private TemplateEntityMeta meta = new TemplateEntityMeta();

	public List<TemplateEntity> getAll() {
		List<TemplateEntity> templateEntities = Datastore.query(meta)
				.sort(meta.name.asc)
				.asList();
		return templateEntities;
	}

	public TemplateEntity get(Key key) {
		TemplateEntity templateEntity = null;
		try {
			templateEntity = Datastore.get(TemplateEntity.class, key);
		} catch (EntityNotFoundRuntimeException e) {
			return null;
		}

		return templateEntity;
	}

	public TemplateEntity getByName(String name) {
		TemplateEntity templateEntity = Datastore.query(meta).filter(meta.name.equal(name)).asSingle();
		return templateEntity;
	}

	public TemplateEntity insert(TemplateEntity templateEntity) {
		Transaction tx = Datastore.beginTransaction();
		Datastore.put(templateEntity);
		tx.commit();

		return templateEntity;
	}

	public TemplateEntity edit(TemplateEntity templateEntity) throws ConcurrentModificationException {
		Transaction tx = Datastore.beginTransaction();
		try {
			Datastore.get(tx, TemplateEntity.class, templateEntity.getKey(), templateEntity.getVersion());

			Datastore.put(tx, templateEntity);
			tx.commit();
		} catch (ConcurrentModificationException e) {
			if (tx.isActive()) {
				tx.rollback();
			}
			throw e;
		}

		return templateEntity;
	}

	public void delete(Key key, Long version) throws ConcurrentModificationException {
		Transaction tx = Datastore.beginTransaction();
		try {
			TemplateEntity templateEntity = Datastore.get(tx, meta, key, version);

			Datastore.delete(tx, templateEntity.getKey());
			tx.commit();
		} catch (ConcurrentModificationException e) {
			if (tx.isActive()) {
				tx.rollback();
			}
			throw e;
		}
	}
}
