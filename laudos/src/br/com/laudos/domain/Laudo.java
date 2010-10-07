package br.com.laudos.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.framework.hibernate.domain.Bean;


@Entity
@Table(name="laudo")
public class Laudo extends Bean {

	/**
	 *
	 */
	private static final long serialVersionUID = 6809473276488022598L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_laudo")
	private int idLaudo;

	@Column(name="laudo",nullable=false)
	private String laudo;

	@Column(name="nm_laudo",nullable=false)
	private String nmLaudo;

	/**
	 * @return the idLaudo
	 */
	public int getIdLaudo() {
		return idLaudo;
	}

	/**
	 * @param idLaudo the idLaudo to set
	 */
	public void setIdLaudo(int idLaudo) {
		this.idLaudo = idLaudo;
	}

	/**
	 * @return the laudo
	 */
	public String getLaudo() {
		return laudo;
	}

	/**
	 * @param laudo the laudo to set
	 */
	public void setLaudo(String laudo) {
		this.laudo = laudo;
	}

	/**
	 * @return the nmLaudo
	 */
	public String getNmLaudo() {
		return nmLaudo;
	}

	/**
	 * @return the nmLaudo
	 */
	public String getLaudoRed() {
		try{
			return  laudo.substring(1,50);
		}catch (Exception e) {
			return  laudo.trim();
		}


	}

	/**
	 * @param nmLaudo the nmLaudo to set
	 */
	public void setNmLaudo(String nmLaudo) {
		this.nmLaudo = nmLaudo;
	}

}
