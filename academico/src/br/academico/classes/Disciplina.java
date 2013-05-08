package br.academico.classes;

import java.util.List;

public class Disciplina {

	
	private int idDiscipina;
	
	private String nmDisciplina;
	
	private int cargaHr;
	
	private int bloco;
	
	private List<Professor> professores;

	/**
	 * @return the idDiscipina
	 */
	public int getIdDiscipina() {
		return idDiscipina;
	}

	/**
	 * @param idDiscipina the idDiscipina to set
	 */
	public void setIdDiscipina(int idDiscipina) {
		this.idDiscipina = idDiscipina;
	}

	/**
	 * @return the nmDisciplina
	 */
	public String getNmDisciplina() {
		return nmDisciplina;
	}

	/**
	 * @param nmDisciplina the nmDisciplina to set
	 */
	public void setNmDisciplina(String nmDisciplina) {
		this.nmDisciplina = nmDisciplina;
	}

	/**
	 * @return the cargaHr
	 */
	public int getCargaHr() {
		return cargaHr;
	}

	/**
	 * @param cargaHr the cargaHr to set
	 */
	public void setCargaHr(int cargaHr) {
		this.cargaHr = cargaHr;
	}

	/**
	 * @return the bloco
	 */
	public int getBloco() {
		return bloco;
	}

	/**
	 * @param bloco the bloco to set
	 */
	public void setBloco(int bloco) {
		this.bloco = bloco;
	}

	/**
	 * @return the professores
	 */
	public List<Professor> getProfessores() {
		return professores;
	}

	/**
	 * @param professores the professores to set
	 */
	public void setProfessores(List<Professor> professores) {
		this.professores = professores;
	}
	
	
	
	
}
