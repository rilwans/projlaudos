package br.com.laudos.domain;

import javax.faces.bean.ManagedBean;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.framework.hibernate.domain.Bean;


@Entity
@Table(name="convenio")
@ManagedBean
public class Convenio extends Bean {

	/**
	 *
	 */
	private static final long serialVersionUID = 6173572906357036501L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_convenio", unique=true,nullable=true )
	private int idConvenio;

	@Column(name="nm_convenio")
	private String nmConvenio;

	/**
	 * @return the idConvenio
	 */
	public int getIdConvenio() {
		return idConvenio;
	}

	/**
	 * @param idConvenio the idConvenio to set
	 */
	public void setIdConvenio(int idConvenio) {
		this.idConvenio = idConvenio;
	}

	/**
	 * @return the nmConvenio
	 */
	public String getNmConvenio() {
		return nmConvenio;
	}

	/**
	 * @param nmConvenio the nmConvenio to set
	 */
	public void setNmConvenio(String nmConvenio) {
		this.nmConvenio = nmConvenio;
	}

}
