package br.com.benz.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

import br.com.benz.domain.enums.MotPrescricao;
import br.com.benz.domain.enums.OndePrescreve;
import br.com.benz.domain.enums.Sexo;
import br.com.benz.domain.enums.TempoIndicacao;
import br.com.framework.hibernate.domain.Bean;

@Entity
@Table(name = "medico")
public class Medico extends Bean {

	

	/**
	 * 
	 */
	private static final long serialVersionUID = -3389145142912166227L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_medico", unique = true, nullable = false)
	private int idMedico;

	@Column(name = "fl_sexo", columnDefinition = "integer", nullable = false)
	@Type(type = "br.com.framework.hibernate.domain.enums.EnumUserType", parameters = {
			@Parameter(name = "enumClass", value = "br.com.benz.domain.enums.Sexo"),
			@Parameter(name = "identifierMethod", value = "toInt"), @Parameter(name = "valueOfMethod", value = "fromInt") })
	private Sexo sexo;

	@Column(name = "idade", nullable = false, length = 40)
	private int idade;

	@Column(name = "aprazolan", nullable = true)
	private Boolean aprazolam;

	@Column(name = "obsAprazolam", nullable = true, length = 200)
	private String obsAprazolam;

	@Column(name = "bromazepam", nullable = true)
	private Boolean bromazepam;

	@Column(name = "obsBromazepam", nullable = true, length = 200)
	private String obsBromazepam;

	@Column(name = "clobazam", nullable = true)
	private Boolean clobazam;

	@Column(name = "obsClobazam", nullable = true, length = 200)
	private String obsClobazam;

	@Column(name = "clonazepam", nullable = true)
	private Boolean clonazepam;

	@Column(name = "obsClonazepam", nullable = true, length = 200)
	private String obsClonazepam;

	@Column(name = "clordiazepoxido", nullable = true)
	private Boolean clordiazepoxido;

	@Column(name = "obsClordiazepoxido", nullable = true, length = 200)
	private String obsClordiazepoxido;

	@Column(name = "cloxazolam", nullable = true)
	private Boolean cloxazolam;

	@Column(name = "obsCloxazolam", nullable = true, length = 200)
	private String obsCloxazolam;

	@Column(name = "diazepam", nullable = true)
	private Boolean diazepam;

	@Column(name = "obsDiazepam", nullable = true, length = 200)
	private String obsDiazepam;

	@Column(name = "lorazepam", nullable = true)
	private Boolean lorazepam;

	@Column(name = "obsLorazepam", nullable = true, length = 200)
	private String obsLorazepam;

	@Column(name = "flunitrazepam", nullable = true)
	private Boolean flunitrazepam;

	@Column(name = "obsFlunitrazepam", nullable = true, length = 200)
	private String obsFlunitrazepam;

	@Column(name = "flurazepam", nullable = true)
	private Boolean flurazepam;

	@Column(name = "obsFlurazepam", nullable = true, length = 200)
	private String obsFlurazepam;

	@Column(name = "midazolam", nullable = true)
	private Boolean midazolam;

	@Column(name = "obsMidazolam", nullable = true, length = 200)
	private String obsMidazolam;

	@Column(name = "nitrazepam", nullable = true)
	private Boolean nitrazepam;

	@Column(name = "obsNitrazepam", nullable = true, length = 200)
	private String obsNitrazepam;

	@Column(name = "motPrescicao", columnDefinition = "integer", nullable = false)
	@Type(type = "br.com.framework.hibernate.domain.enums.EnumUserType", parameters = {
			@Parameter(name = "enumClass", value = "br.com.benz.domain.enums.MotPrescricao"),
			@Parameter(name = "identifierMethod", value = "toInt"), @Parameter(name = "valueOfMethod", value = "fromInt") })
	private MotPrescricao motPrescicao;
	
	@Column(name = "ondePrecreve", columnDefinition = "integer", nullable = false)
	@Type(type = "br.com.framework.hibernate.domain.enums.EnumUserType", parameters = {
			@Parameter(name = "enumClass", value = "br.com.benz.domain.enums.OndePrescreve"),
			@Parameter(name = "identifierMethod", value = "toInt"), @Parameter(name = "valueOfMethod", value = "fromInt") })
	private OndePrescreve ondePrecreve;
	
	@Column(name = "tempoIndicacao", columnDefinition = "integer", nullable = false)
	@Type(type = "br.com.framework.hibernate.domain.enums.EnumUserType", parameters = {
			@Parameter(name = "enumClass", value = "br.com.benz.domain.enums.TempoIndicacao"),
			@Parameter(name = "identifierMethod", value = "toInt"), @Parameter(name = "valueOfMethod", value = "fromInt") })
	private TempoIndicacao tempoIndicacao;
	
	@Column(name = "recomendacao", nullable = true)
	private Boolean recomendacao;
	
	@Column(name = "parentesAmigosPedemRec", nullable = true)
	private Boolean parentesAmigosPedemRec;
	
	@Column(name = "emiteRecParenteAmigo", nullable = true)
	private Boolean emiteRecParenteAmigo;
	
	@Column(name = "pacientePedemRecTerceiros", nullable = true)
	private Boolean pacientePedemRecTerceiros;
	
	@Column(name = "emiteReceitaTerceiros", nullable = true)
	private Boolean emiteReceitaTerceiros;
	
	@Column(name = "localAbordagem", columnDefinition = "integer", nullable = false)
	@Type(type = "br.com.framework.hibernate.domain.enums.EnumUserType", parameters = {
			@Parameter(name = "enumClass", value = "br.com.benz.domain.enums.OndePrescreve"),
			@Parameter(name = "identifierMethod", value = "toInt"), @Parameter(name = "valueOfMethod", value = "fromInt") })
	private OndePrescreve localAbordagem;

	public int getIdMedico() {
		return idMedico;
	}

	public void setIdMedico(int idMedico) {
		this.idMedico = idMedico;
	}

	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public Boolean getAprazolam() {
		return aprazolam;
	}

	public void setAprazolam(Boolean aprazolam) {
		this.aprazolam = aprazolam;
	}

	public String getObsAprazolam() {
		return obsAprazolam;
	}

	public void setObsAprazolam(String obsAprazolam) {
		this.obsAprazolam = obsAprazolam;
	}

	public Boolean getBromazepam() {
		return bromazepam;
	}

	public void setBromazepam(Boolean bromazepam) {
		this.bromazepam = bromazepam;
	}

	public String getObsBromazepam() {
		return obsBromazepam;
	}

	public void setObsBromazepam(String obsBromazepam) {
		this.obsBromazepam = obsBromazepam;
	}

	public Boolean getClobazam() {
		return clobazam;
	}

	public void setClobazam(Boolean clobazam) {
		this.clobazam = clobazam;
	}

	public String getObsClobazam() {
		return obsClobazam;
	}

	public void setObsClobazam(String obsClobazam) {
		this.obsClobazam = obsClobazam;
	}

	public Boolean getClonazepam() {
		return clonazepam;
	}

	public void setClonazepam(Boolean clonazepam) {
		this.clonazepam = clonazepam;
	}

	public String getObsClonazepam() {
		return obsClonazepam;
	}

	public void setObsClonazepam(String obsClonazepam) {
		this.obsClonazepam = obsClonazepam;
	}

	public Boolean getClordiazepoxido() {
		return clordiazepoxido;
	}

	public void setClordiazepoxido(Boolean clordiazepoxido) {
		this.clordiazepoxido = clordiazepoxido;
	}

	public String getObsClordiazepoxido() {
		return obsClordiazepoxido;
	}

	public void setObsClordiazepoxido(String obsClordiazepoxido) {
		this.obsClordiazepoxido = obsClordiazepoxido;
	}

	public Boolean getCloxazolam() {
		return cloxazolam;
	}

	public void setCloxazolam(Boolean cloxazolam) {
		this.cloxazolam = cloxazolam;
	}

	public String getObsCloxazolam() {
		return obsCloxazolam;
	}

	public void setObsCloxazolam(String obsCloxazolam) {
		this.obsCloxazolam = obsCloxazolam;
	}

	public Boolean getDiazepam() {
		return diazepam;
	}

	public void setDiazepam(Boolean diazepam) {
		this.diazepam = diazepam;
	}

	public String getObsDiazepam() {
		return obsDiazepam;
	}

	public void setObsDiazepam(String obsDiazepam) {
		this.obsDiazepam = obsDiazepam;
	}

	public Boolean getLorazepam() {
		return lorazepam;
	}

	public void setLorazepam(Boolean lorazepam) {
		this.lorazepam = lorazepam;
	}

	public String getObsLorazepam() {
		return obsLorazepam;
	}

	public void setObsLorazepam(String obsLorazepam) {
		this.obsLorazepam = obsLorazepam;
	}

	public Boolean getFlunitrazepam() {
		return flunitrazepam;
	}

	public void setFlunitrazepam(Boolean flunitrazepam) {
		this.flunitrazepam = flunitrazepam;
	}

	public String getObsFlunitrazepam() {
		return obsFlunitrazepam;
	}

	public void setObsFlunitrazepam(String obsFlunitrazepam) {
		this.obsFlunitrazepam = obsFlunitrazepam;
	}

	public Boolean getFlurazepam() {
		return flurazepam;
	}

	public void setFlurazepam(Boolean flurazepam) {
		this.flurazepam = flurazepam;
	}

	public String getObsFlurazepam() {
		return obsFlurazepam;
	}

	public void setObsFlurazepam(String obsFlurazepam) {
		this.obsFlurazepam = obsFlurazepam;
	}

	public Boolean getMidazolam() {
		return midazolam;
	}

	public void setMidazolam(Boolean midazolam) {
		this.midazolam = midazolam;
	}

	public String getObsMidazolam() {
		return obsMidazolam;
	}

	public void setObsMidazolam(String obsMidazolam) {
		this.obsMidazolam = obsMidazolam;
	}

	public Boolean getNitrazepam() {
		return nitrazepam;
	}

	public void setNitrazepam(Boolean nitrazepam) {
		this.nitrazepam = nitrazepam;
	}

	public String getObsNitrazepam() {
		return obsNitrazepam;
	}

	public void setObsNitrazepam(String obsNitrazepam) {
		this.obsNitrazepam = obsNitrazepam;
	}

	public MotPrescricao getMotPrescicao() {
		return motPrescicao;
	}

	public void setMotPrescicao(MotPrescricao motPrescicao) {
		this.motPrescicao = motPrescicao;
	}

	public OndePrescreve getOndePrecreve() {
		return ondePrecreve;
	}

	public void setOndePrecreve(OndePrescreve ondePrecreve) {
		this.ondePrecreve = ondePrecreve;
	}

	public TempoIndicacao getTempoIndicacao() {
		return tempoIndicacao;
	}

	public void setTempoIndicacao(TempoIndicacao tempoIndicacao) {
		this.tempoIndicacao = tempoIndicacao;
	}

	public Boolean getRecomendacao() {
		return recomendacao;
	}

	public void setRecomendacao(Boolean recomendacao) {
		this.recomendacao = recomendacao;
	}

	public Boolean getParentesAmigosPedemRec() {
		return parentesAmigosPedemRec;
	}

	public void setParentesAmigosPedemRec(Boolean parentesAmigosPedemRec) {
		this.parentesAmigosPedemRec = parentesAmigosPedemRec;
	}

	public Boolean getEmiteRecParenteAmigo() {
		return emiteRecParenteAmigo;
	}

	public void setEmiteRecParenteAmigo(Boolean emiteRecParenteAmigo) {
		this.emiteRecParenteAmigo = emiteRecParenteAmigo;
	}

	public Boolean getPacientePedemRecTerceiros() {
		return pacientePedemRecTerceiros;
	}

	public void setPacientePedemRecTerceiros(Boolean pacientePedemRecTerceiros) {
		this.pacientePedemRecTerceiros = pacientePedemRecTerceiros;
	}

	public Boolean getEmiteReceitaTerceiros() {
		return emiteReceitaTerceiros;
	}

	public void setEmiteReceitaTerceiros(Boolean emiteReceitaTerceiros) {
		this.emiteReceitaTerceiros = emiteReceitaTerceiros;
	}

	public OndePrescreve getLocalAbordagem() {
		return localAbordagem;
	}

	public void setLocalAbordagem(OndePrescreve localAbordagem) {
		this.localAbordagem = localAbordagem;
	}
	

	
}
