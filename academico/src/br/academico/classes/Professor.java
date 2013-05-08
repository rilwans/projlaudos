package br.academico.classes;

import java.util.Date;
import java.util.List;

public class Professor {

	
	private int idProfessor;
	
	private String nmProfessor;
	
	private String endereco;
	
	private String cidade;
	
	private String estado;
	
	private String CEP;
	
	private Date dtNascimento;
	
	private String CPF;
	
	private String telefone;
	
	private String RG;
	
	private String sexo;
	
	private List<Disciplina> disciplinas;

	/**
	 * @return the idProfessor
	 */
	public int getIdProfessor() {
		return idProfessor;
	}

	/**
	 * @param idProfessor the idProfessor to set
	 */
	public void setIdProfessor(int idProfessor) {
		this.idProfessor = idProfessor;
	}

	/**
	 * @return the nmProfessor
	 */
	public String getNmProfessor() {
		return nmProfessor;
	}

	/**
	 * @param nmProfessor the nmProfessor to set
	 */
	public void setNmProfessor(String nmProfessor) {
		this.nmProfessor = nmProfessor;
	}

	/**
	 * @return the endereco
	 */
	public String getEndereco() {
		return endereco;
	}

	/**
	 * @param endereco the endereco to set
	 */
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	/**
	 * @return the cidade
	 */
	public String getCidade() {
		return cidade;
	}

	/**
	 * @param cidade the cidade to set
	 */
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	/**
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * @param estado the estado to set
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	/**
	 * @return the cEP
	 */
	public String getCEP() {
		return CEP;
	}

	/**
	 * @param cEP the cEP to set
	 */
	public void setCEP(String cEP) {
		CEP = cEP;
	}

	/**
	 * @return the dtNascimento
	 */
	public Date getDtNascimento() {
		return dtNascimento;
	}

	/**
	 * @param dtNascimento the dtNascimento to set
	 */
	public void setDtNascimento(Date dtNascimento) {
		this.dtNascimento = dtNascimento;
	}

	/**
	 * @return the cPF
	 */
	public String getCPF() {
		return CPF;
	}

	/**
	 * @param cPF the cPF to set
	 */
	public void setCPF(String cPF) {
		CPF = cPF;
	}

	/**
	 * @return the telefone
	 */
	public String getTelefone() {
		return telefone;
	}

	/**
	 * @param telefone the telefone to set
	 */
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	/**
	 * @return the rG
	 */
	public String getRG() {
		return RG;
	}

	/**
	 * @param rG the rG to set
	 */
	public void setRG(String rG) {
		RG = rG;
	}

	/**
	 * @return the sexo
	 */
	public String getSexo() {
		return sexo;
	}

	/**
	 * @param sexo the sexo to set
	 */
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	/**
	 * @return the disciplinas
	 */
	public List<Disciplina> getDisciplinas() {
		return disciplinas;
	}

	/**
	 * @param disciplinas the disciplinas to set
	 */
	public void setDisciplinas(List<Disciplina> disciplinas) {
		this.disciplinas = disciplinas;
	}

	
	
}
