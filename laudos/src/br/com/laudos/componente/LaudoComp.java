package br.com.laudos.componente;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import br.com.laudos.domain.Laudo;
import br.com.laudos.facade.Facade;

@ManagedBean
@SessionScoped
public class LaudoComp implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -8934696360095001816L;

	private List<Laudo> laudos;

	private Laudo laudo = new Laudo();


	/**
	 * @return the laudos
	 */
	@SuppressWarnings("unchecked")
	public List<Laudo> getLaudos() {
		try {
			laudos = (List<Laudo>) Facade.getInstance().listAll(Laudo.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return laudos;
	}

	/**
	 * @param laudos
	 *            the laudos to set
	 */
	public void setLaudos(List<Laudo> laudos) {
		this.laudos = laudos;
	}

	/**
	 * @return the laudo
	 */
	public Laudo getLaudo() {
		return laudo;
	}

	/**
	 * @param laudo
	 *            the laudo to set
	 */
	public void setLaudo(Laudo laudo) {
		this.laudo = laudo;
	}

	public String gravaLaudo(ActionEvent actionEvent) {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			Facade.getInstance().update(laudo);
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Dados Salvos com Sucesso!", ""));
		} catch (Exception e) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Não foi Possivel Salvar os Dados!!", ""));
		}

		return "lstmodelos";
	}


}
