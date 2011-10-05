package br.hibernate.operation;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.PropertyValueException;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.exception.GenericJDBCException;
import org.hibernate.proxy.HibernateProxy;

import br.hibernate.domain.Bean;
import br.hibernate.utils.HibernateUtils;

public class HibernateOperation {

	private Logger logger = Logger.getLogger(HibernateOperation.class);
	
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
			restoreSession(e);
			throw e;
		}
		
	}
	
	public Serializable insert(Bean obj) throws Exception {
		Transaction transaction = null;
		try {
			transaction = (Transaction) HibernateUtils.getSessao().beginTransaction();
			Serializable idGerado = HibernateUtils.getSessao().save(obj);
			transaction.commit();
			return idGerado;
		} catch (HibernateException e) {
			if (HibernateUtils.transacaoAtiva())
				HibernateUtils.rollbackTransaction();
			restoreSession(e);
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
			restoreSession(e);
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
			restoreSession(e);
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
			restoreSession(e);
			throw e;
		}

	}
	
	public Bean loadById(Class<? extends Bean> klass, String propretyName, Serializable id) throws Exception {
		Map<String, Object> proprety = new HashMap<String, Object>();
		proprety.put(propretyName, id);
		return loadById(klass, proprety);
	}
	
	
	public Criteria getCriteria(Class<? extends Bean> klass) throws Exception {
		return HibernateUtils.getSessao().createCriteria(klass);
	}
	
	@SuppressWarnings("unchecked")
	public List<Bean> listByCriteria(Criteria criteria) throws Exception{
		try{
			List<Bean> list = criteria.list();
			return list;
		}catch (HibernateException e) {
			if (HibernateUtils.transacaoAtiva())
				HibernateUtils.rollbackTransaction();
			restoreSession(e);
			throw e; 
		}
	}
	
	
	private void restoreSession(HibernateException exception) throws Exception {
		logger.error("Erro na Transação.", exception);

		if (HibernateUtils.transacaoAtiva())
			HibernateUtils.rollbackTransaction();

		String msg = "";
		if (exception instanceof ConstraintViolationException) {
			ConstraintViolationException e = (ConstraintViolationException) exception;

			String erro = e.getSQLException().getMessage();

			int start = erro.indexOf("**") + 2;
			int end = erro.indexOf(".") - 1;
			msg = erro.substring(start, end);

		} else if (exception instanceof PropertyValueException) {
			PropertyValueException e = (PropertyValueException) exception;

			String propriedade = e.getPropertyName() != null ? e.getPropertyName() : "";
			String entidade = e.getEntityName() != null ? e.getEntityName().substring(e.getEntityName().lastIndexOf(".") + 1)
					: "";

			if (e.getMessage().indexOf("not-null") >= 0) {
				msg = "Na entidade " + entidade + " a propriedade " + propriedade + " não pode ser nulo.";
			} else
				msg = "Entidade: " + entidade + ", Propriedade: " + propriedade + ", Erro: " + e.getMessage();
		} else if (exception instanceof GenericJDBCException) {
			GenericJDBCException e = (GenericJDBCException) exception;

			String erro = e.getSQLException().getMessage();
			msg = erro.replace("*", "");
		}

		// throw new BDException(!msg.isEmpty() ? msg :
		// Arrays.deepToString(exception
		// .getMessages()), exception);
		throw new Exception(!msg.isEmpty() ? msg : exception.getMessage(), exception);
	}
	
}
