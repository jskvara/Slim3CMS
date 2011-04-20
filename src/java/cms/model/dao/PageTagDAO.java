package cms.model.dao;

import cms.model.meta.PageTagEntityMeta;
import cms.model.model.PageTagEntity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Transaction;
import java.util.Collection;
import java.util.List;
import org.slim3.datastore.Datastore;

public class PageTagDAO implements DAO {
	private PageTagEntityMeta meta = PageTagEntityMeta.get();

	public List<PageTagEntity> getByPage(Key page) {
		List<PageTagEntity> pageTagEntities = Datastore.query(meta).filter(meta.pageRef.equal(page)).asList();
		return pageTagEntities;
	}

	public PageTagEntity insert(PageTagEntity pageTagEntity) {
		Transaction tx = Datastore.beginTransaction();
		Datastore.put(tx, pageTagEntity);
		tx.commit();

		return pageTagEntity;
	}

	public void deleteAll(Collection<PageTagEntity> pageTagEntities) {
		Transaction tx = Datastore.beginTransaction();
		for(PageTagEntity pageTagEntity : pageTagEntities) {
			Datastore.delete(tx, pageTagEntity.getKey());
		}
		tx.commit();
	}
}
