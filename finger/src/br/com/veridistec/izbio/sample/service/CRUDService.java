package br.com.veridistec.izbio.sample.service;

import java.io.Serializable;
import java.util.List;

/**
 * CRUD Service possui definicoes dos metodos que um servico de (C)reate,
 * (R)etrieve, (U)pdate e (D)elete.
 * 
 * @author user
 * 
 * @param <T>
 *            Tipo de entidade para qual o CRUDService sera utilizado
 */
public interface CRUDService<T, TID> extends Serializable {

	public abstract T create();

	public abstract T save(T t);

	public abstract T findById(TID id);

	public abstract void remove(T t);

	public abstract Long count();

	public abstract Long count(FilterCRUD filter);

	public abstract List<T> findAll(FilterCRUD filter, Integer offset, Integer quantity, String sortSuffix);

	public List<CRUDListener<T, TID>> getListeners();

	public void addListener(CRUDListener<T, TID> listener);

	public void remListener(CRUDListener<T, TID> listener);

	public void clearListeners();
}