package br.hibernate.operation;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.proxy.HibernateProxy;

import br.hibernate.domain.Bean;
import br.hibernate.utils.HibernateUtils;

public class HibernateOperation {

	
	public List<?> listAll(Class<? extends Bean> klass) throws Exception{
		
		try {
			Criteria criteria;
			criteria = HibernateUtils.getSessao().createCriteria(klass);

			@SuppressWarnings("unchecked")
			List<Bean> result = criteria.list();

			return result;
		} catch (HibernateException e) {
			if (HibernateUtils.transacaoAtiva())
				HibernateUtils.rollbackTransaction();
			throw e;
		}
		
	}
	
	public Serializable create(Bean obj) throws Exception {
		Transaction transaction = null;
		try {
			transaction = (Transaction) HibernateUtils.getSessao().beginTransaction();
			Serializable idGerado = HibernateUtils.getSessao().save(obj);
			transaction.commit();
			return idGerado;
		} catch (HibernateException e) {
			if (HibernateUtils.transacaoAtiva())
				HibernateUtils.rollbackTransaction();
			throw e;
		}
	}

	public Bean update(final Bean obj) throws Exception {
		Bean bean = null;
		Transaction transaction = null;
		try {
			transaction = (Transaction) HibernateUtils.getSessao().beginTransaction();
			bean = (Bean) HibernateUtils.getSessao().merge(obj);
			transaction.commit();
		} catch (HibernateException e) {
			if (HibernateUtils.transacaoAtiva())
				HibernateUtils.rollbackTransaction();
			throw e;
		}
		return bean;
	}

	public void delete(final Bean obj) throws Exception {
		Transaction transaction = null ;
		try {
			transaction = (Transaction) HibernateUtils.getSessao().beginTransaction();
			HibernateUtils.getSessao().delete(obj);
			transaction.commit();
		} catch (HibernateException e) {
			if (HibernateUtils.transacaoAtiva())
				HibernateUtils.rollbackTransaction();
			throw e;
		}
	}
	
	@SuppressWarnings("unchecked")
	public Bean loadById(Class<? extends Bean> klass, Map<String, Object> propretyName) throws Exception {
		try {
			Bean obj;
			try {
				
				Criteria criteria = HibernateUtils.getSessao().createCriteria(klass);

				for (String fieldName : propretyName.keySet()) {
					Object object = propretyName.get(fieldName);
					criteria.add(Restrictions.eq(fieldName, object));
				}

				List<Bean> list = criteria.list();

				if (list instanceof HibernateProxy) {
					list = (List<Bean>) ((HibernateProxy) list).getHibernateLazyInitializer().getImplementation();
				}
				if (list != null && list.size() > 0)
					return list.get(0);
				else
					return null;

			} catch (ObjectNotFoundException e) {
				if (HibernateUtils.transacaoAtiva())
					HibernateUtils.rollbackTransaction();
				obj = null;
			}
			return obj;
		} catch (HibernateException e) {
			if (HibernateUtils.transacaoAtiva())
				HibernateUtils.rollbackTransaction();
			throw e;
		}

	}
	
	public Bean loadById(Class<? extends Bean> klass, String propretyName, Serializable id) throws Exception {
		Map<String, Object> proprety = new HashMap<String, Object>();
		proprety.put(propretyName, id);
		return loadById(klass, proprety);
	}
	
}
