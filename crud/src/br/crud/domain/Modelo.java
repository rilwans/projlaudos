package br.crud.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="modelo")
public class Modelo extends br.hibernate.domain.Bean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2352907052376956709L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_modelo", unique = true, nullable = false)
	private int idModelo;
	
	@Column(name = "txt_modelo", nullable = false)
	private String txtModelo;
	
	@Column(name = "nm_modelo", nullable = false)
	private String nmModelo;

	public int getIdModelo() {
		return idModelo;
	}

	public void setIdModelo(int idModelo) {
		this.idModelo = idModelo;
	}

	public String getTxtModelo() {
		return txtModelo;
	}

	public void setTxtModelo(String txtModelo) {
		this.txtModelo = txtModelo;
	}

	public String getNmModelo() {
		return nmModelo;
	}

	public void setNmModelo(String nmModelo) {
		this.nmModelo = nmModelo;
	}
	
	
}
