package br.com.camillaeantonio.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.framework.hibernate.domain.Bean;

@Entity
@Table(name="confirma")
public class Confirma extends Bean{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -2844812682830844361L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_confirma", unique = true, nullable = false)
	private int idConfirma;

	@Column(name = "nm_convidado", nullable = false, length = 40)
	private String nmConvidado;

	@Column(name = "dt_confirma", nullable = false, length = 11)
	private Date dtConfirma;

	public int getIdConfirma() {
		return idConfirma;
	}

	public void setIdConfirma(int idConfirma) {
		this.idConfirma = idConfirma;
	}

	public String getNmConvidado() {
		return nmConvidado;
	}

	public void setNmConvidado(String nmConvidado) {
		this.nmConvidado = nmConvidado;
	}

	public Date getDtConfirma() {
		return dtConfirma;
	}

	public void setDtConfirma(Date dtConfirma) {
		this.dtConfirma = dtConfirma;
	}


}
