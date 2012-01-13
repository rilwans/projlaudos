package br.com.veridistec.izbio.sample.service;

import javax.persistence.EntityManager;

public interface CRUDListener<T, TID> {
	public void preSave(EntityManager em, T entity);

	public void afterSave(EntityManager em, T entity);

	public void preDelete(EntityManager em, T entity);

	public void afterDelete(EntityManager em, T entity);

	public void preFind(EntityManager em, TID id);

	public void afterFind(EntityManager em, T entity);
}
