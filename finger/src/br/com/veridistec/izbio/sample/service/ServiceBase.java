package br.com.veridistec.izbio.sample.service;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

public class ServiceBase implements Serializable {

	private static final long serialVersionUID = 3381021848929791761L;

	@PersistenceContext(unitName = "default")
	private static EntityManagerFactory emf;

	static {
		emf = Persistence.createEntityManagerFactory("default");
	}

	public synchronized EntityManager getEM() {
		EntityManager em = emf.createEntityManager();
//		em.getTransaction().begin();
		return em;
	}

	public void close(EntityManager em) {
		if (!em.getTransaction().isActive()) {
//			em.getTransaction().begin();
		} else {
			em.getTransaction().commit();
		}
//		em.createNativeQuery("SHUTDOWN;").executeUpdate();
//		em.getTransaction().commit();
		em.close();
	}

}