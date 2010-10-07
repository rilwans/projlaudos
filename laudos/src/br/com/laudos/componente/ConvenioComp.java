package br.com.laudos.componente;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import br.com.laudos.domain.Convenio;
import br.com.laudos.facade.Facade;

@ManagedBean
public class ConvenioComp implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -5448527512851891719L;

	private List<Convenio> convenios;

	private Convenio convenio = new Convenio();

	/**
	 * @return the convenios
	 */
	@SuppressWarnings("unchecked")
	public List<Convenio> getConvenios() {
		try {
			convenios = (List<Convenio>) Facade.getInstance().listAll(Convenio.class);
			return convenios;
		} catch (Exception e) {
			e.printStackTrace();

		}
		return null;
	}

	/**
	 * @param convenios
	 *            the convenios to set
	 */
	public void setConvenios(List<Convenio> convenios) {
		this.convenios = convenios;
	}

	/**
	 * @return the convenio
	 */
	public Convenio getConvenio() {
		return convenio;
	}

	/**
	 * @param convenio
	 *            the convenio to set
	 */
	public void setConvenio(Convenio convenio) {
		this.convenio = convenio;
	}

	@SuppressWarnings("unchecked")
	public void gravaConvenio(){
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			Facade.getInstance().update(convenio);
			convenios = (List<Convenio>) Facade.getInstance().listAll(Convenio.class);
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Dados Salvos com Sucesso!", ""));
		} catch (Exception e) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Não foi Possivel Salvar os Dados!!", ""));
		}

	}

	@SuppressWarnings("unchecked")
	public void novoConvenio(){
		FacesContext context = FacesContext.getCurrentInstance();
		Convenio novo = new Convenio();
		novo.setNmConvenio(convenio.getNmConvenio());
		try{
			Facade.getInstance().insert(novo);
			convenios = (List<Convenio>) Facade.getInstance().listAll(Convenio.class);
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Dados Salvos com Sucesso!", ""));
		} catch (Exception e) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Não foi Possivel Salvar os Dados!!", ""));
		}
	}

	@SuppressWarnings("unchecked")
	public void deleteConvenio(){
		FacesContext context = FacesContext.getCurrentInstance();
		try{
			Facade.getInstance().delete(convenio);
			convenios = (List<Convenio>) Facade.getInstance().listAll(Convenio.class);
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Dados Excluidos com Sucesso!", ""));
		} catch (Exception e) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Não foi Possivel Excluir os Dados!!", ""));
		}
	}

}
