package br.crud.system;

import java.io.Serializable;
import java.util.List;

import br.hibernate.operation.HibernateOperation;

public class Controler {
	
	public static Controler controler = null;
	
	public static Controler getInstance() {
		if (controler == null) {
			controler = new Controler();
		}
		return controler;
	}
	
	public Serializable insert(br.hibernate.domain.Bean bean) throws Exception{
		HibernateOperation hibernateOperation = new HibernateOperation();
		return (Serializable) hibernateOperation.insert(bean);
	}
	
	public br.hibernate.domain.Bean update(br.hibernate.domain.Bean bean) throws Exception{
		HibernateOperation hibernateOperation = new HibernateOperation();
		return (br.hibernate.domain.Bean) hibernateOperation.update(bean);
	}
	
	public void delete(br.hibernate.domain.Bean bean) throws Exception{
		HibernateOperation hibernateOperation = new HibernateOperation();
		hibernateOperation.delete(bean);
	}

	@SuppressWarnings("unchecked")
	public List<?> listById(Class<? extends br.hibernate.domain.Bean> klass, String propretyName, Serializable id) throws Exception{
		HibernateOperation hibernateOperation = new HibernateOperation();
		return (List<br.hibernate.domain.Bean>) hibernateOperation.loadById(klass, propretyName, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<?> listAll(Class<? extends br.hibernate.domain.Bean> klass) throws Exception{
		HibernateOperation hibernateOperation = new HibernateOperation();
		return (List<br.hibernate.domain.Bean>) hibernateOperation.listAll(klass);
	}
	
}
