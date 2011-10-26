package cms.model.dao;

import cms.model.model.AuthorEntity;
import cms.model.meta.AuthorEntityMeta;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Transaction;
import java.util.ConcurrentModificationException;
import java.util.List;
import org.slim3.datastore.Datastore;
import org.slim3.datastore.EntityNotFoundRuntimeException;

public class AuthorDAO implements DAO {

	private AuthorEntityMeta meta = AuthorEntityMeta.get();

	public List<AuthorEntity> getAll() {
		List<AuthorEntity> authorEntities = Datastore.query(meta).sort(meta.email.asc).asList();
		return authorEntities;
	}

	public AuthorEntity get(Key key) {
		AuthorEntity authorEntity = null;
		try {
			authorEntity = Datastore.get(AuthorEntity.class, key);
		} catch (EntityNotFoundRuntimeException e) {
			return null;
		}

		return authorEntity;
	}

	public AuthorEntity getByEmail(String email) {
		AuthorEntity authorEntity = Datastore.query(meta).filter(meta.email.equal(email)).asSingle();
		return authorEntity;
	}

	public AuthorEntity insert(AuthorEntity authorEntity) {
		Transaction tx = Datastore.beginTransaction();
		Datastore.put(tx, authorEntity);
		tx.commit();

		return authorEntity;
	}

	public AuthorEntity edit(AuthorEntity authorEntity) throws ConcurrentModificationException {
		Transaction tx = Datastore.beginTransaction();
		try {
			Datastore.get(tx, AuthorEntity.class, authorEntity.getKey(), authorEntity.getVersion());

			Datastore.put(tx, authorEntity);
			tx.commit();
		} catch (ConcurrentModificationException e) {
			if (tx.isActive()) {
				tx.rollback();
			}
			throw e;
		}

		return authorEntity;
	}

	public void delete(Key key, Long version) throws ConcurrentModificationException {
		Transaction tx = Datastore.beginTransaction();
		try {
			AuthorEntity authorEntity = Datastore.get(tx, meta, key, version);
			Datastore.delete(tx, authorEntity.getKey());
			tx.commit();
		} catch (ConcurrentModificationException e) {
			if (tx.isActive()) {
				tx.rollback();
			}
			throw e;
		}
	}
}
