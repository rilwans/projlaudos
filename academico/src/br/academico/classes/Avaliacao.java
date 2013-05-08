package br.academico.classes;

import java.util.Date;

public class Avaliacao {

	private int idAvaliacao;
	
	private MatCurricular matCurricular;
	
	private float nota;
	
	private Date dtAvaliacao;

	/**
	 * @return the idAvaliacao
	 */
	public int getIdAvaliacao() {
		return idAvaliacao;
	}

	/**
	 * @param idAvaliacao the idAvaliacao to set
	 */
	public void setIdAvaliacao(int idAvaliacao) {
		this.idAvaliacao = idAvaliacao;
	}

	/**
	 * @return the matCurricular
	 */
	public MatCurricular getMatCurricular() {
		return matCurricular;
	}

	/**
	 * @param matCurricular the matCurricular to set
	 */
	public void setMatCurricular(MatCurricular matCurricular) {
		this.matCurricular = matCurricular;
	}

	/**
	 * @return the nota
	 */
	public float getNota() {
		return nota;
	}

	/**
	 * @param nota the nota to set
	 */
	public void setNota(float nota) {
		this.nota = nota;
	}

	/**
	 * @return the dtAvaliacao
	 */
	public Date getDtAvaliacao() {
		return dtAvaliacao;
	}

	/**
	 * @param dtAvaliacao the dtAvaliacao to set
	 */
	public void setDtAvaliacao(Date dtAvaliacao) {
		this.dtAvaliacao = dtAvaliacao;
	}
	
	
}
