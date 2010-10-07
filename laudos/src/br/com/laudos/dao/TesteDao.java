package br.com.laudos.dao;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;

import br.com.framework.hibernate.HibernateUtil;
import br.com.framework.hibernate.dao.DaoInterface;
import br.com.framework.hibernate.domain.Bean;
import br.com.laudos.domain.Teste;
import br.com.laudos.facade.Facade;

@ManagedBean

public class TesteDao implements DaoInterface, Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -2040523878860251132L;

	private List<Teste> testes;

	private Teste teste = new Teste();

	private String texto;

	private int id;

	private String toolbar = "complete";

	@Override
	public void delete(Bean bean) throws Exception {
		// TODO Auto-generated method stub

	}

	public String opa() throws Exception {
		if (teste != null) {
			try {
				Facade.getInstance().insert(teste);
			} catch (Exception e) {
				System.out.println(e.toString());
			}

		}
		return "index";
	}

	public String testeVai() throws Exception {
		System.out.println(teste.getDescricao());
		System.out.println(getId());
		return "editor";
	}

	public String chamaEditor() throws Exception {
		System.out.println(teste.getIdTeste());
		return "editor";
	}

	public String excluiTeste() throws Exception {
		if (teste != null) {
			Teste rem = (Teste) Facade.getInstance().loadById(Teste.class, "idTeste", teste.getIdTeste());
			System.out.println(rem.getIdTeste());
			HibernateUtil.beginTransaction();
			try {
				Facade.getInstance().delete(rem);
				HibernateUtil.commitTransaction();
			} catch (Exception e) {
				HibernateUtil.rollbackTransaction();
				System.out.println(e.toString());
			}

		}
		return "insdex";
	}

	public String editarTeste() throws Exception {
		if (teste != null) {
			int i = teste.getIdTeste();
			setTeste((Teste) Facade.getInstance().loadById(Teste.class, "idTeste", i));
			System.out.println("entrou");
			return "editor";
		}else
			return "index";
	}

	@Override
	public Bean update(Bean bean) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @param testes
	 *            the testes to set
	 */

	public void setTestes(List<Teste> testes) {

		this.testes = testes;

	}

	/**
	 * @return the testes
	 */
	@SuppressWarnings("unchecked")
	public List<Teste> getTestes() {
		try {
			setTestes((List<Teste>) Facade.getInstance().listAll(Teste.class));
			Teste tst = new Teste();
			setTeste(tst);
			return this.testes;
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return null;
	}

	public String Load() {
		teste = new Teste();
		return "index";
	}

	/**
	 * @param teste
	 *            the teste to set
	 */
	public void setTeste(Teste teste) {
		this.teste = teste;
	}

	/**
	 * @return the teste
	 */
	public Teste getTeste() {
		return teste;
	}

	@Override
	public Bean insert(Bean bean) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @param toolbar the toolbar to set
	 */
	public void setToolbar(String toolbar) {
		this.toolbar = toolbar;
	}

	/**
	 * @return the toolbar
	 */
	public String getToolbar() {
		return toolbar;
	}

	/**
	 * @param texto the texto to set
	 */
	public void setTexto(String texto) {
		this.texto = texto;
	}

	/**
	 * @return the texto
	 */
	public String getTexto() {
		return texto;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	public String modelo(){
		System.out.println("teste");
		return "/xhtml/modelo";
	}

}

