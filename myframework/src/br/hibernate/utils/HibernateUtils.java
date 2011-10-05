package br.hibernate.utils;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import br.hibernate.domain.Bean;

public class HibernateUtils {

	private static SessionFactory sessionFactory;
	
	private static final ThreadLocal<Session> sessoes = new ThreadLocal<Session>();

	private static final ThreadLocal<Transaction> transacoes = new ThreadLocal<Transaction>();
	
	public static List<Class<? extends Bean>> beans = new ArrayList<Class<? extends Bean>>();
	
	public HibernateUtils(){
	}
	
	
	public static void getSessionFactory(){
		if (sessionFactory==null){
			try{
				Configuration cfg = new Configuration();
				
				
				for (Class<? extends Bean> bean : beans) {
					cfg.addAnnotatedClass(bean);
				}
				
				sessionFactory = cfg.buildSessionFactory();
	
			}catch (Throwable err) {
				System.err.println("Erro no getSessionFactory "+err);
				throw new ExceptionInInitializerError(err);
			}
		}
	}
	
	public static Session getSessao() {
		Session session = (Session) sessoes.get();
		if (session == null || !session.isOpen()) {
			session = sessionFactory.openSession();
			sessoes.set(session);
		}
		return (Session) sessoes.get();
	}
	
	public static void beginTransaction() {
		Transaction transaction = getSessao().beginTransaction();
		transacoes.set(transaction);
	}

	public static void commitTransaction() {
		Transaction transaction = (Transaction) transacoes.get();
		if (transaction != null && !transaction.wasCommitted() && !transaction.wasRolledBack()) {
			transaction.commit();
			transacoes.set(null);
		}
	}

	public static boolean transacaoAtiva() {
		Transaction transaction = (Transaction) transacoes.get();
		return (transaction != null && !transaction.wasCommitted() && !transaction.wasRolledBack());
	}

	public static void rollbackTransaction() {
		Transaction transaction = (Transaction) transacoes.get();
		if (transaction != null && !transaction.wasCommitted() && !transaction.wasRolledBack()) {
			transaction.rollback();
			transacoes.set(null);
		}
	}

	public static void closeSession() {
		Session session = (Session) sessoes.get();
		if (session != null && session.isOpen()) {
			sessoes.set(null);
			session.close();
		}
	}	
	
}
