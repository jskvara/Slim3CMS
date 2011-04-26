package cms.model.dao;

import cms.model.model.PageEntity;
import cms.model.meta.PageEntityMeta;
import cms.model.model.PageTagEntity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Transaction;
import java.util.List;
import org.slim3.datastore.Datastore;

public class PageDAO implements DAO {
	private PageEntityMeta pageMeta = PageEntityMeta.get();
	
	public List<PageEntity> getAll() {
		List<PageEntity> pageEntities = Datastore.query(pageMeta)
				./*sort(pageMeta.position.desc).*/asList();
		return pageEntities;
	}

	public PageEntity get(Key key) {
		PageEntity pageEntity = Datastore.get(PageEntity.class, key);
		return pageEntity;
	}

	public PageEntity getByUrl(String url) {
		PageEntity pageEntity = Datastore.query(pageMeta)
				.filter(pageMeta.url.equal(url)).asSingle();
		return pageEntity;
	}

	public PageEntity getVisibleByUrl(String url) {
		PageEntity pageEntity = Datastore.query(pageMeta)
				.filter(pageMeta.url.equal(url))
				.filter(pageMeta.visible.equal(true))
				.asSingle();
		return pageEntity;
	}

	public PageEntity insert(PageEntity pageEntity) {
		Transaction tx = Datastore.beginTransaction();
		Datastore.put(tx, pageEntity);
		tx.commit();

		return pageEntity;
	}

	public PageEntity edit(PageEntity pageEntity) {
		Transaction tx = Datastore.beginTransaction();
		Datastore.put(tx, pageEntity);
		tx.commit();

		return pageEntity;
	}

	public void delete(Key key) {
		Transaction tx = Datastore.beginTransaction();
		PageEntity pageEntity = Datastore.get(tx, pageMeta, key);
		for (PageTagEntity pageTagEntity : pageEntity.getPageTagListRef().getModelList()) {
			Datastore.delete(tx, pageTagEntity.getKey());
		}
		Datastore.delete(tx, pageEntity.getKey());
		tx.commit();
	}
}
