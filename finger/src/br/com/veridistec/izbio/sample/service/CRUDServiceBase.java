package br.com.veridistec.izbio.sample.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.RollbackException;

public abstract class CRUDServiceBase<T, TID> extends ServiceBase implements CRUDService<T, TID> {
	private static final long serialVersionUID = 2261593008753066271L;

	protected Class<T> entityBeanType;

	private List<CRUDListener<T, TID>> listeners = new ArrayList<CRUDListener<T, TID>>();

	// public CrudServiceImpl() {
	// this.entityBeanType = (Class<T>) ((ParameterizedType)
	// getClass().getGenericSuperclass())
	// .getActualTypeArguments()[0];
	// }

	public CRUDServiceBase(Class<T> entityBeanType) {
		this.entityBeanType = entityBeanType;
		clearListeners();
	}

	public T create() {
		try {
			return entityBeanType.newInstance();
		} catch (Exception e) {
			// log.error("Error while creating entity[" +
			// entityBeanType.getName()
			// + "].", e);
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public T findById(TID id) {
		EntityManager em = getEM();
		preFind(em, id);
		T it = em.find(entityBeanType, id);
		afterFind(em, it);
		close(em);
		return it;
	}

	public T save(T t) {
		EntityManager em = getEM();
		em.getTransaction().begin();
		boolean clearId = false;
		try {
			preSave(em, t);
			// if (!getEM().contains(t)) {
			if (getId(t) == null) {
				clearId = true;
				em.persist(t);
			} else {
				t = em.merge(t);
			}
			afterSave(em, t);
			em.getTransaction().commit();
			return t;
		} catch (Exception exc) {
			exc.printStackTrace();
			// log.error("Error while saving entity[" + entityBeanType.getName()
			// + ", id:'" + getId(t) + "']", exc);
			try {
				em.getTransaction().rollback();
			} catch (RollbackException re) {
				re.printStackTrace();
				// log.error("Error while rolling back transaction.", re);
			}
			if (clearId) {
				setId(t, null);
			}
			throw new RuntimeException(exc.getMessage());
		} finally {
			close(em);
		}
	}

	public void remove(T t) {
		EntityManager em = getEM();
		em.getTransaction().begin();
		try {
			t = em.find(this.entityBeanType, getId(t));
			preDelete(em, t);
			em.remove(t);
			afterDelete(em, t);
			em.getTransaction().commit();
		} catch (Exception exc) {
			exc.printStackTrace();
			// log.error("Error while removing entity[" +
			// entityBeanType.getName()
			// + ", id:'" + getId(t) + "']", exc);
			try {
				em.getTransaction().rollback();
			} catch (RollbackException re) {
				re.printStackTrace();
				// log.error("Error while rolling back transaction.", re);
			}
			throw new RuntimeException(exc.getMessage());
		} finally {
			close(em);
		}
	}

	public List<T> findAll(FilterCRUD filter, Integer offset, Integer quantity, String sortSuffix) {
		return internalFindAll(filter, offset, quantity, null, sortSuffix, entityBeanType);
	}

	@SuppressWarnings("unchecked")
	protected List<T> internalFindAll(FilterCRUD filter, Integer offset, Integer quantity, String whereClause, String sortSuffix, Class<? extends T> entity) {
		EntityManager em = getEM();
		em.getTransaction().begin();
		if (sortSuffix != null) {
			sortSuffix = sortSuffix.replaceAll("ASCENDING", "ASC");
			sortSuffix = sortSuffix.replaceAll("DESCENDING", "DESC");
		}
		if (filter != null) {
			String nClause = filter.addWhereClause("t", whereClause);
			if (whereClause == null || whereClause.trim().length() == 0) {
				whereClause = " ";
			}
			whereClause += whereClause + nClause;
		}
		Query q = em.createQuery("select t from " + entity.getSimpleName() + " t" + (whereClause != null ? " where" + whereClause : "")
				+ (sortSuffix != null ? " order by" + sortSuffix : ""));
		if (offset != null && quantity != null) {
			q.setMaxResults(quantity);
			q.setFirstResult(offset);
		}
		if (filter != null) {
			filter.addParameters(q);
		}
		List<T> list = q.getResultList();
		close(em);
		return list;
	}

	public Long count() {
		return internalCount(null, entityBeanType);
	}

	public Long count(FilterCRUD filter) {
		return internalCount(filter, entityBeanType);
	}

	protected Long internalCount(FilterCRUD filter, Class<? extends T> entity) {
		EntityManager em = getEM();
		String whereClause = null;
		if (filter != null) {
			whereClause = filter.addWhereClause("t", whereClause);
		}
		Query q = em.createQuery("select count(t) from " + entity.getSimpleName() + " t" + (whereClause != null ? " where " + whereClause : ""));
		if (filter != null) {
			filter.addParameters(q);
		}
		Long res = (Long) q.getSingleResult();
		close(em);
		return res;
	}

	public abstract TID getId(T t);

	public abstract void setId(T t, TID id);

	// CRUD Listener methods!

	public List<CRUDListener<T, TID>> getListeners() {
		return new ArrayList<CRUDListener<T, TID>>(listeners);
	}

	public void addListener(CRUDListener<T, TID> listener) {
		if (listener != null) {
			this.listeners.add(listener);
		}
	}

	public void remListener(CRUDListener<T, TID> listener) {
		if (listener != null) {
			this.listeners.remove(listener);
		}
	}

	public void clearListeners() {
		this.listeners = new ArrayList<CRUDListener<T, TID>>();
	}

	private void preSave(EntityManager em, T entity) {
		for (CRUDListener<T, TID> lis : listeners) {
			lis.preSave(em, entity);
		}
	}

	private void afterSave(EntityManager em, T entity) {
		for (CRUDListener<T, TID> lis : listeners) {
			lis.afterSave(em, entity);
		}
	}

	private void preDelete(EntityManager em, T entity) {
		for (CRUDListener<T, TID> lis : listeners) {
			lis.preDelete(em, entity);
		}
	}

	private void afterDelete(EntityManager em, T entity) {
		for (CRUDListener<T, TID> lis : listeners) {
			lis.afterDelete(em, entity);
		}
	}

	private void preFind(EntityManager em, TID id) {
		for (CRUDListener<T, TID> lis : listeners) {
			lis.preFind(em, id);
		}
	}

	private void afterFind(EntityManager em, T entity) {
		for (CRUDListener<T, TID> lis : listeners) {
			lis.afterFind(em, entity);
		}
	}
}