package br.com.laudos.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.framework.hibernate.domain.Bean;


@Entity
@Table(name="teste")
public class Teste extends Bean {


	/**
	 *
	 */
	private static final long serialVersionUID = 3140976259474771228L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id_teste")
	private int idTeste;

	@Column(name="nm_teste",nullable=false)
	private String nmTeste;

	@Column(name = "ds_evolucao", nullable = true, columnDefinition = "text")
	private String descricao;

	/**
	 * @return the idTeste
	 */
	public int getIdTeste() {
		return idTeste;
	}

	/**
	 * @param idTeste the idTeste to set
	 */
	public void setIdTeste(int idTeste) {
		this.idTeste = idTeste;
	}

	/**
	 * @return the nmTeste
	 */
	public String getNmTeste() {
		return nmTeste;
	}

	/**
	 * @param nmTeste the nmTeste to set
	 */
	public void setNmTeste(String nmTeste) {
		this.nmTeste = nmTeste;
	}

	/**
	 * @param descricao the descricao to set
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}
}
