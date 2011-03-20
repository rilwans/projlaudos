package br.com.camillaeantonio.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.framework.hibernate.domain.Bean;

@Entity
@Table(name="foto")
public class Foto extends Bean {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7234077089286606581L;

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_foto", unique = true, nullable = false)
	private int idFoto;
	
	@Column(name = "nome", nullable = false, length = 40)
	private String nome;


	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}


	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}


	/**
	 * @param idFoto the idFoto to set
	 */
	public void setIdFoto(int idFoto) {
		this.idFoto = idFoto;
	}


	/**
	 * @return the idFoto
	 */
	public int getIdFoto() {
		return idFoto;
	}
	
}
