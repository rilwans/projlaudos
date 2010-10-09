package br.com.laudos.componente;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.laudos.domain.Convenio;
import br.com.laudos.domain.Laudo;
import br.com.laudos.domain.Paciente;
import br.com.laudos.facade.Facade;

@ManagedBean
@SessionScoped
public class PacienteComp implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -4556537631224894438L;

	private boolean novo = false;

	private Paciente paciente = new Paciente();

	private List<Paciente> pacientes;

	private List<Laudo> modelos;

	private Laudo modelo = new Laudo();

	private int idConvenio = 0;

	private int idLaudo = 0;

	/**
	 * @return the paciente
	 */
	public Paciente getPaciente() {
		return paciente;
	}

	/**
	 * @param paciente
	 *            the paciente to set
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
	 * @param pacientes
	 *            the pacientes to set
	 */
	public void setPacientes(List<Paciente> pacientes) {
		this.pacientes = pacientes;
	}

	/**
	 * @param novo
	 *            the novo to set
	 */
	public void setNovo(boolean novo) {
		this.novo = novo;
	}

	/**
	 * @return the novo
	 */
	public boolean getNovo() {
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
	 * @param modelos
	 *            the modelos to set
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
	 * @param modelo
	 *            the modelo to set
	 */
	public void setModelo(Laudo modelo) {
		this.modelo = modelo;
	}

	@SuppressWarnings("unchecked")
	public String novoLaudo() {
		FacesContext context = FacesContext.getCurrentInstance();
		setNovo(true);
		setIdConvenio(0);
		setIdLaudo(0);
		try {
			modelos = (List<Laudo>) Facade.getInstance().listAll(Laudo.class);
			setPaciente(new Paciente());
		} catch (Exception e) {
			context
					.addMessage(null,
							new FacesMessage(FacesMessage.SEVERITY_ERROR, "Não foi possivel iniciar um novo Laudo!", ""));
		}
		return "cadlaudo";
	}

	public String chamaCadLaudo(){
		novoLaudo();
		return "/xhtml/cadlaudo";
	}

	public String excluirLaudo() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			Paciente pac = (Paciente) Facade.getInstance().loadById(Paciente.class,"idPaciente", paciente.getIdPaciente());
			Facade.getInstance().delete(pac);
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Laudo excluido com sucesso!", ""));
		} catch (Exception e) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Não foi possivel exclir o Laudo!", ""));
		}
		return "lstlaudos";
	}

	public void gravarLaudo() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			if (getNovo()) {
				Convenio conv = (Convenio) Facade.getInstance().loadById(Convenio.class, "idConvenio", getIdConvenio());
				paciente.setConvenio(conv);
				Laudo lad = (Laudo) Facade.getInstance().loadById(Laudo.class, "idLaudo", getIdLaudo());
				paciente.setLaudo(lad);
				Integer i = (Integer) Facade.getInstance().insert(paciente);
				paciente.setIdPaciente(i.intValue());
			} else {
				Convenio conv = (Convenio) Facade.getInstance().loadById(Convenio.class, "idConvenio", getIdConvenio());
				paciente.setConvenio(conv);
				Laudo lad = (Laudo) Facade.getInstance().loadById(Laudo.class, "idLaudo", getIdLaudo());
				paciente.setLaudo(lad);
				Facade.getInstance().update(paciente);
				setPaciente((Paciente) Facade.getInstance().loadById(Paciente.class, "idPaciente", paciente.getIdPaciente()));
			}
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Dados salvos com sucesso!", ""));
		} catch (Exception e) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Não foi possivel localizar os dados!", ""));
		}
	}

	public String editarLaudo() {
		FacesContext context = FacesContext.getCurrentInstance();

		try {
			paciente = (Paciente) Facade.getInstance().loadById(Paciente.class, "idPaciente", paciente.getIdPaciente());
			setNovo(false);
			setIdLaudo(paciente.getLaudo().getIdLaudo());
			setIdConvenio(paciente.getConvenio().getIdConvenio());
		} catch (Exception e) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Não foi possivel localizar os dados!", ""));
			return "lstlaudos";
		}
		return "cadlaudo";
	}

	public void escolheModelo() {
		FacesContext context = FacesContext.getCurrentInstance();
		Laudo lad;
		try {
			if (getIdLaudo() != 0) {
				lad = (Laudo) Facade.getInstance().loadById(Laudo.class, "idLaudo", getIdLaudo());
				paciente.setLaudo(lad);
				paciente.setTxtLaudo(lad.getLaudo());
			} else
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Escolha um modelo válido!", ""));
		} catch (Exception e) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao carregar Modelo!", ""));
		}

	}

	@SuppressWarnings("unchecked")
	public String chamaLista(){
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			pacientes = (List<Paciente>) Facade.getInstance().listAll(Paciente.class);

			return "lstlaudos";
		} catch (Exception e) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Não foi possivel listar os pacientes!", ""));
		}
		return "lstlaudos";
	}

	/**
	 * @param idConvenio
	 *            the idConvenio to set
	 */
	public void setIdConvenio(int idConvenio) {
		this.idConvenio = idConvenio;
	}

	/**
	 * @return the idConvenio
	 */
	public int getIdConvenio() {
		return idConvenio;
	}

	/**
	 * @param idLaudo
	 *            the idLaudo to set
	 */
	public void setIdLaudo(int idLaudo) {
		this.idLaudo = idLaudo;
	}

	/**
	 * @return the idLaudo
	 */
	public int getIdLaudo() {
		return idLaudo;
	}

}
