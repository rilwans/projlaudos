package br.com.laudos.componente;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.laudos.domain.Laudo;
import br.com.laudos.domain.Paciente;
import br.com.laudos.facade.Facade;

@ManagedBean
@SessionScoped
public class PacienteComp implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = -4556537631224894438L;

	private boolean novo=false;

	private Paciente paciente = new Paciente();

	private List<Paciente> pacientes;

	private List<Laudo> modelos;

	private Laudo modelo = new Laudo();

	private String txtModelo = "";

	/**
	 * @return the paciente
	 */
	public Paciente getPaciente() {
		return paciente;
	}

	/**
	 * @param paciente the paciente to set
	 */
	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	/**
	 * @return the pacientes
	 */
	@SuppressWarnings("unchecked")
	public List<Paciente> getPacientes() {
		try {
			pacientes = (List<Paciente>) Facade.getInstance().listAll(Paciente.class);

			return pacientes;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @param pacientes the pacientes to set
	 */
	public void setPacientes(List<Paciente> pacientes) {
		this.pacientes = pacientes;
	}

	/**
	 * @param novo the novo to set
	 */
	public void setNovo(boolean novo) {
		this.novo = novo;
	}

	/**
	 * @return the novo
	 */
	public boolean isNovo() {
		return novo;
	}

	/**
	 * @return the modelos
	 */
	@SuppressWarnings("unchecked")
	public List<Laudo> getModelos() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			modelos = (List<Laudo>) Facade.getInstance().listAll(Laudo.class);
		} catch (Exception e) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Não foi possivel localizar os dados!", ""));
		}
		return modelos;
	}

	/**
	 * @param modelos the modelos to set
	 */
	public void setModelos(List<Laudo> modelos) {
		this.modelos = modelos;
	}

	/**
	 * @return the modelo
	 */
	public Laudo getModelo() {
		return modelo;
	}

	/**
	 * @param modelo the modelo to set
	 */
	public void setModelo(Laudo modelo) {
		this.modelo = modelo;
	}

	@SuppressWarnings("unchecked")
	public String novoLaudo(){
		FacesContext context = FacesContext.getCurrentInstance();
		setNovo(true);
		try {
			modelos = (List<Laudo>) Facade.getInstance().listAll(Laudo.class);
			paciente = new Paciente();
		} catch (Exception e) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Não foi possivel iniciar um novo Laudo!", ""));
		}
		return "cadlaudo";
	}

	public String excluirLaudo(){
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			Facade.getInstance().delete(paciente);
		} catch (Exception e) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Não foi possivel exclir o Laudo!", ""));
		}
		return "lstlaudos";
	}

	public String gravarLaudo(){
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			Facade.getInstance().insert(paciente);
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Dados salvos com sucesso!", ""));
		} catch (Exception e) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Não foi possivel localizar os dados!", ""));
		}
		return "lstlaudos";
	}

	public String editarLaudo(){
		FacesContext context = FacesContext.getCurrentInstance();

		try {
			paciente = (Paciente) Facade.getInstance().loadById(Paciente.class, "idPaciente",paciente.getIdPaciente());
			System.out.println(paciente.getNmPaciente());
		} catch (Exception e) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Não foi possivel localizar os dados!", ""));
			return "lstlaudos";
		}
		return "cadlaudo";
	}

	/**
	 * @param txtModelo the txtModelo to set
	 */
	public void setTxtModelo(String txtModelo) {
		this.txtModelo = txtModelo;
	}

	/**
	 * @return the txtModelo
	 */
	public String getTxtModelo() {
		return txtModelo;
	}

	public void escolheModelo() {
		paciente.setLaudo(txtModelo);
	}


}
