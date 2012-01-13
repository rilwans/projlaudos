package br.com.veridistec.izbio.sample;

import javax.persistence.EntityManager;
import javax.swing.AbstractListModel;

import br.com.veridistec.izbio.sample.service.CRUDListener;
import br.com.veridistec.izbio.sample.service.CRUDService;

public class CRUDListModel<T, TID> extends AbstractListModel {
	private static final long serialVersionUID = -1022210189139331321L;
	private CRUDService<T, TID> servico;

	public CRUDListModel(CRUDService<T, TID> serv) {
		this.servico = serv;
		CRUDListener<T, TID> lis = new CRUDListener<T, TID>() {
			public void afterDelete(EntityManager em, T entity) {
				fireContentsChanged(this, 0, getSize() - 1);
			}
			public void afterFind(EntityManager em, T entity) {
			}
			public void afterSave(EntityManager em, T entity) {
				fireContentsChanged(this, 0, getSize() - 1);
			}
			public void preDelete(EntityManager em, T entity) {
			}
			public void preFind(EntityManager em, TID id) {
			}
			public void preSave(EntityManager em, T entity) {
			}
		};
		servico.addListener(lis);
	}

	public Object getElementAt(int index) {
		return getCrudElement(index);
	}

	public T getCrudElement(int index) {
		return servico.findAll(null, index, 1, " t.id ASC").get(0);
	}

	public int getSize() {
		int c = servico.count(null).intValue();
		return c;
	}
}
