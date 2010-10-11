package br.com.laudos.domain;

import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.ForeignKey;

import br.com.framework.hibernate.domain.Bean;
import br.com.laudos.facade.Facade;

@Entity
@Table(name = "paciente")
@ManagedBean
public class Paciente extends Bean {

	/**
	 *
	 */
	private static final long serialVersionUID = -4867823747296103923L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_paciente", unique = true, nullable = false)
	private int idPaciente;

	@Column(name = "laudo", nullable = false)
	private String txtLaudo;

	@Transient
	private List<Paciente> listapac;

	@Column(name = "nm_paciente", nullable = false)
	private String nmPaciente;

	@Column(name = "numero", nullable = false)
	private String numero;

	@Column(name = "nm_medico", nullable = false)
	private String nmMedico;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_convenio", nullable = false, insertable = true, updatable = true)
	@ForeignKey(name = "fk_consulta_Convenio")
	private Convenio convenio;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_laudo", nullable = false, insertable = true, updatable = true)
	@ForeignKey(name = "fk_paciente_laudo")
	private Laudo laudo;

	@Column(name = "idade", nullable = true)
	private int idade;

	@Column(name = "dt_exame", nullable = true)
	private Date dtExame ;


	/**
	 * @return the idPaciente
	 */
	public int getIdPaciente() {
		return idPaciente;
	}

	/**
	 * @param idPaciente
	 *            the idPaciente to set
	 */
	public void setIdPaciente(int idPaciente) {
		this.idPaciente = idPaciente;
	}


	/**
	 * @return the nmPaciente
	 */
	public String getNmPaciente() {
		return nmPaciente;
	}

	/**
	 * @param nmPaciente
	 *            the nmPaciente to set
	 */
	public void setNmPaciente(String nmPaciente) {
		this.nmPaciente = nmPaciente;
	}

	/**
	 * @return the numero
	 */
	public String getNumero() {
		return numero;
	}

	/**
	 * @param numero
	 *            the numero to set
	 */
	public void setNumero(String numero) {
		this.numero = numero;
	}

	/**
	 * @return the nmMedico
	 */
	public String getNmMedico() {
		return nmMedico;
	}

	/**
	 * @param nmMedico
	 *            the nmMedico to set
	 */
	public void setNmMedico(String nmMedico) {
		this.nmMedico = nmMedico;
	}

	/**
	 * @return the convenio
	 */

	/**
	 * @return the idade
	 */
	public int getIdade() {
		return idade;
	}

	/**
	 * @param idade
	 *            the idade to set
	 */
	public void setIdade(int idade) {
		this.idade = idade;
	}

	@SuppressWarnings("unchecked")
	public List<Paciente> getListapac() {
		try {
			listapac = (List<Paciente>) Facade.getInstance().listAll(Paciente.class);
			return listapac;
		} catch (Exception e) {

			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @param listapac
	 *
	 */
	public void setListapac(List<Paciente> listapac) {
		this.listapac = listapac;
	}

	/**
	 * @param convenio
	 *            the convenio to set
	 */
	public void setConvenio(Convenio convenio) {
		this.convenio = convenio;
	}

	/**
	 * @return the convenio
	 */
	public Convenio getConvenio() {
		return convenio;
	}

	/**
	 * @param txtLaudo the txtLaudo to set
	 */
	public void setTxtLaudo(String txtLaudo) {
		this.txtLaudo = txtLaudo;
	}

	/**
	 * @return the txtLaudo
	 */
	public String getTxtLaudo() {
		return txtLaudo;
	}

	/**
	 * @param laudo the laudo to set
	 */
	public void setLaudo(Laudo laudo) {
		this.laudo = laudo;
	}

	/**
	 * @return the laudo
	 */
	public Laudo getLaudo() {
		return laudo;
	}

	/**
	 * @param dtExame the dtExame to set
	 */
	public void setDtExame(Date dtExame) {
		this.dtExame = dtExame;
	}

	/**
	 * @return the dtExame
	 */
	public Date getDtExame() {
		return dtExame;
	}

}
