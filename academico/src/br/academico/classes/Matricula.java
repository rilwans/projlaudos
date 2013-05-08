package br.academico.classes;

import java.util.Date;

public class Matricula {

	private int idMatricula;
	
	private Aluno aluno;
	
	private Curso curso;
	
	private String periodo;
	
	private Date iniPeriodo;
	
	private Date fimPeriodo;

	/**
	 * @return the idMatricula
	 */
	public int getIdMatricula() {
		return idMatricula;
	}

	/**
	 * @param idMatricula the idMatricula to set
	 */
	public void setIdMatricula(int idMatricula) {
		this.idMatricula = idMatricula;
	}

	/**
	 * @return the aluno
	 */
	public Aluno getAluno() {
		return aluno;
	}

	/**
	 * @param aluno the aluno to set
	 */
	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	/**
	 * @return the curso
	 */
	public Curso getCurso() {
		return curso;
	}

	/**
	 * @param curso the curso to set
	 */
	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	/**
	 * @return the periodo
	 */
	public String getPeriodo() {
		return periodo;
	}

	/**
	 * @param periodo the periodo to set
	 */
	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	/**
	 * @return the iniPeriodo
	 */
	public Date getIniPeriodo() {
		return iniPeriodo;
	}

	/**
	 * @param iniPeriodo the iniPeriodo to set
	 */
	public void setIniPeriodo(Date iniPeriodo) {
		this.iniPeriodo = iniPeriodo;
	}

	/**
	 * @return the fimPeriodo
	 */
	public Date getFimPeriodo() {
		return fimPeriodo;
	}

	/**
	 * @param fimPeriodo the fimPeriodo to set
	 */
	public void setFimPeriodo(Date fimPeriodo) {
		this.fimPeriodo = fimPeriodo;
	}
	
		
}
