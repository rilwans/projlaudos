package br.academico.classes;

public class MatCurricular {

	
	private int idMatCurricular;
	
	private Matricula matricula;
	
	private Disciplina disciplina;
	
	private String situacao;

	/**
	 * @return the idMatCurricular
	 */
	public int getIdMatCurricular() {
		return idMatCurricular;
	}

	/**
	 * @param idMatCurricular the idMatCurricular to set
	 */
	public void setIdMatCurricular(int idMatCurricular) {
		this.idMatCurricular = idMatCurricular;
	}

	/**
	 * @return the matricula
	 */
	public Matricula getMatricula() {
		return matricula;
	}

	/**
	 * @param matricula the matricula to set
	 */
	public void setMatricula(Matricula matricula) {
		this.matricula = matricula;
	}

	/**
	 * @return the disciplina
	 */
	public Disciplina getDisciplina() {
		return disciplina;
	}

	/**
	 * @param disciplina the disciplina to set
	 */
	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

	/**
	 * @return the situacao
	 */
	public String getSituacao() {
		return situacao;
	}

	/**
	 * @param situacao the situacao to set
	 */
	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}
	
}
