package br.com.benz.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

import br.com.benz.domain.enums.CompraSemReceita;
import br.com.benz.domain.enums.Receitador;
import br.com.benz.domain.enums.Sexo;
import br.com.benz.domain.enums.TempoIndicacao;
import br.com.benz.domain.enums.TratamentoQueda;
import br.com.framework.hibernate.domain.Bean;

@Entity
@Table(name = "paciente")
public class Paciente extends Bean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1019045340512042154L;

	/**
	 * 
	 */

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_paciente", unique = true, nullable = false)
	private int idPaciente;

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

	@Column(name = "motDor", nullable = true)
	private Boolean motDor;

	@Column(name = "motFaltaSono", nullable = true)
	private Boolean motFaltaSono;

	@Column(name = "motMedo", nullable = true)
	private Boolean motMedo;

	@Column(name = "motTristeza", nullable = true)
	private Boolean motTristeza;

	@Column(name = "motPreocupacao", nullable = true)
	private Boolean motPreocupacao;

	@Column(name = "motAnsiedade", nullable = true)
	private Boolean motAnsiedade;

	@Column(name = "motPressaoAlta", nullable = true)
	private Boolean motPressaoAlta;

	@Column(name = "motDepressao", nullable = true)
	private Boolean motDepressao;

	@Column(name = "semReceita", nullable = true)
	private Boolean semReceita;

	@Column(name = "usaTodoDia", nullable = true)
	private Boolean usaTodoDia;

	@Column(name = "tempoUsoIndicacao", columnDefinition = "integer", nullable = false)
	@Type(type = "br.com.framework.hibernate.domain.enums.EnumUserType", parameters = {
			@Parameter(name = "enumClass", value = "br.com.benz.domain.enums.TempoIndicacao"),
			@Parameter(name = "identifierMethod", value = "toInt"), @Parameter(name = "valueOfMethod", value = "fromInt") })
	private TempoIndicacao tempoUsoIndicacao;

	@Column(name = "aumetouDoseSozinho", nullable = true)
	private Boolean aumetouDoseSozinho;

	@Column(name = "recomendouRemedio", nullable = true)
	private Boolean recomendouRemedio;

	@Column(name = "motivoRecomendou", nullable = true, length = 200)
	private String motivoRecomendou;

	@Column(name = "ocorreuRecomendacaoEspMed", nullable = true)
	private Boolean ocorreuRecomendacaoEspMed;

	@Column(name = "recomendacaoEspMed", nullable = true, length = 200)
	private String recomendacaoEspMed;

	@Column(name = "receitador", columnDefinition = "integer", nullable = false)
	@Type(type = "br.com.framework.hibernate.domain.enums.EnumUserType", parameters = {
			@Parameter(name = "enumClass", value = "br.com.benz.domain.enums.Receitador"),
			@Parameter(name = "identifierMethod", value = "toInt"), @Parameter(name = "valueOfMethod", value = "fromInt") })
	private Receitador receitador;

	@Column(name = "espeialMesmoMedico", nullable = true, length = 200)
	private String espeialMesmoMedico;

	@Column(name = "ocorreRecusaReceita", nullable = true)
	private Boolean ocorreRecusaReceita;

	@Column(name = "pqRecusaReceita", nullable = true, length = 200)
	private String pqRecusaReceita;

	@Column(name = "compraSemReceita", columnDefinition = "integer", nullable = false)
	@Type(type = "br.com.framework.hibernate.domain.enums.EnumUserType", parameters = {
			@Parameter(name = "enumClass", value = "br.com.benz.domain.enums.CompraSemReceita"),
			@Parameter(name = "identifierMethod", value = "toInt"), @Parameter(name = "valueOfMethod", value = "fromInt") })
	private CompraSemReceita compraSemReceita;

	@Column(name = "achaDesnecessarioReceita", nullable = true)
	private Boolean achaDesnecessarioReceita;

	@Column(name = "conheceRiscosMedicamentos", nullable = true)
	private Boolean conheceRiscosMedicamentos;

	@Column(name = "qualRisco", nullable = true, length = 200)
	private String qualRisco;

	@Column(name = "perdeuEfeito", nullable = true)
	private Boolean perdeuEfeito;

	@Column(name = "caiu12Meses", nullable = true)
	private Boolean caiu12Meses;

	@Column(name = "tratamentoQueda", columnDefinition = "integer", nullable = false)
	@Type(type = "br.com.framework.hibernate.domain.enums.EnumUserType", parameters = {
			@Parameter(name = "enumClass", value = "br.com.benz.domain.enums.TratamentoQueda"),
			@Parameter(name = "identifierMethod", value = "toInt"), @Parameter(name = "valueOfMethod", value = "fromInt") })
	private TratamentoQueda tratamentoQueda;

	@Column(name = "obsTratamentoQueda", nullable = true, length = 200)
	private String obsTratamentoQueda;

	@Column(name = "doencaCronica", nullable = true)
	private Boolean doencaCronica;

	@Column(name = "usaMedicaAtual", nullable = true)
	private Boolean usaMedicaAtual;

	public int getIdPaciente() {
		return idPaciente;
	}

	public void setIdPaciente(int idPaciente) {
		this.idPaciente = idPaciente;
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

	public Boolean isAprazolam() {
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

	public Boolean isBromazepam() {
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

	public Boolean isClobazam() {
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

	public Boolean isClonazepam() {
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

	public Boolean isClordiazepoxido() {
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

	public Boolean isCloxazolam() {
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

	public Boolean isDiazepam() {
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

	public Boolean isLorazepam() {
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

	public Boolean isFlunitrazepam() {
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

	public Boolean isFlurazepam() {
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

	public Boolean isMidazolam() {
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

	public Boolean isNitrazepam() {
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

	public Boolean isMotDor() {
		return motDor;
	}

	public void setMotDor(Boolean motDor) {
		this.motDor = motDor;
	}

	public Boolean isMotFaltaSono() {
		return motFaltaSono;
	}

	public void setMotFaltaSono(Boolean motFaltaSono) {
		this.motFaltaSono = motFaltaSono;
	}

	public Boolean isMotMedo() {
		return motMedo;
	}

	public void setMotMedo(Boolean motMedo) {
		this.motMedo = motMedo;
	}

	public Boolean isMotTristeza() {
		return motTristeza;
	}

	public void setMotTristeza(Boolean motTristeza) {
		this.motTristeza = motTristeza;
	}

	public Boolean isMotPreocupacao() {
		return motPreocupacao;
	}

	public void setMotPreocupacao(Boolean motPreocupacao) {
		this.motPreocupacao = motPreocupacao;
	}

	public Boolean isMotAnsiedade() {
		return motAnsiedade;
	}

	public void setMotAnsiedade(Boolean motAnsiedade) {
		this.motAnsiedade = motAnsiedade;
	}

	public Boolean isMotPressaoAlta() {
		return motPressaoAlta;
	}

	public void setMotPressaoAlta(Boolean motPressaoAlta) {
		this.motPressaoAlta = motPressaoAlta;
	}

	public Boolean isMotDepressao() {
		return motDepressao;
	}

	public void setMotDepressao(Boolean motDepressao) {
		this.motDepressao = motDepressao;
	}

	public Boolean isSemReceita() {
		return semReceita;
	}

	public void setSemReceita(Boolean semReceita) {
		this.semReceita = semReceita;
	}

	public Boolean isUsaTodoDia() {
		return usaTodoDia;
	}

	public void setUsaTodoDia(Boolean usaTodoDia) {
		this.usaTodoDia = usaTodoDia;
	}

	public TempoIndicacao getTempoUsoIndicacao() {
		return tempoUsoIndicacao;
	}

	public void setTempoUsoIndicacao(TempoIndicacao tempoUsoIndicacao) {
		this.tempoUsoIndicacao = tempoUsoIndicacao;
	}

	public Boolean isAumetouDoseSozinho() {
		return aumetouDoseSozinho;
	}

	public void setAumetouDoseSozinho(Boolean aumetouDoseSozinho) {
		this.aumetouDoseSozinho = aumetouDoseSozinho;
	}

	public Boolean isRecomendouRemedio() {
		return recomendouRemedio;
	}

	public void setRecomendouRemedio(Boolean recomendouRemedio) {
		this.recomendouRemedio = recomendouRemedio;
	}

	public String getMotivoRecomendou() {
		return motivoRecomendou;
	}

	public void setMotivoRecomendou(String motivoRecomendou) {
		this.motivoRecomendou = motivoRecomendou;
	}

	public Boolean isOcorreuRecomendacaoEspMed() {
		return ocorreuRecomendacaoEspMed;
	}

	public void setOcorreuRecomendacaoEspMed(Boolean ocorreuRecomendacaoEspMed) {
		this.ocorreuRecomendacaoEspMed = ocorreuRecomendacaoEspMed;
	}

	public String getRecomendacaoEspMed() {
		return recomendacaoEspMed;
	}

	public void setRecomendacaoEspMed(String recomendacaoEspMed) {
		this.recomendacaoEspMed = recomendacaoEspMed;
	}

	public Receitador getReceitador() {
		return receitador;
	}

	public void setReceitador(Receitador receitador) {
		this.receitador = receitador;
	}

	public String getEspeialMesmoMedico() {
		return espeialMesmoMedico;
	}

	public void setEspeialMesmoMedico(String espeialMesmoMedico) {
		this.espeialMesmoMedico = espeialMesmoMedico;
	}

	public Boolean isOcorreRecusaReceita() {
		return ocorreRecusaReceita;
	}

	public void setOcorreRecusaReceita(Boolean ocorreRecusaReceita) {
		this.ocorreRecusaReceita = ocorreRecusaReceita;
	}

	public String getPqRecusaReceita() {
		return pqRecusaReceita;
	}

	public void setPqRecusaReceita(String pqRecusaReceita) {
		this.pqRecusaReceita = pqRecusaReceita;
	}

	public CompraSemReceita getCompraSemReceita() {
		return compraSemReceita;
	}

	public void setCompraSemReceita(CompraSemReceita compraSemReceita) {
		this.compraSemReceita = compraSemReceita;
	}

	public Boolean isAchaDesnecessarioReceita() {
		return achaDesnecessarioReceita;
	}

	public void setAchaDesnecessarioReceita(Boolean achaDesnecessarioReceita) {
		this.achaDesnecessarioReceita = achaDesnecessarioReceita;
	}

	public Boolean isConheceRiscosMedicamentos() {
		return conheceRiscosMedicamentos;
	}

	public void setConheceRiscosMedicamentos(Boolean conheceRiscosMedicamentos) {
		this.conheceRiscosMedicamentos = conheceRiscosMedicamentos;
	}

	public String getQualRisco() {
		return qualRisco;
	}

	public void setQualRisco(String qualRisco) {
		this.qualRisco = qualRisco;
	}

	public Boolean isPerdeuEfeito() {
		return perdeuEfeito;
	}

	public void setPerdeuEfeito(Boolean perdeuEfeito) {
		this.perdeuEfeito = perdeuEfeito;
	}

	public Boolean isCaiu12Meses() {
		return caiu12Meses;
	}

	public void setCaiu12Meses(Boolean caiu12Meses) {
		this.caiu12Meses = caiu12Meses;
	}

	public TratamentoQueda getTratamentoQueda() {
		return tratamentoQueda;
	}

	public void setTratamentoQueda(TratamentoQueda tratamentoQueda) {
		this.tratamentoQueda = tratamentoQueda;
	}

	public String getObsTratamentoQueda() {
		return obsTratamentoQueda;
	}

	public void setObsTratamentoQueda(String obsTratamentoQueda) {
		this.obsTratamentoQueda = obsTratamentoQueda;
	}

	public Boolean isDoencaCronica() {
		return doencaCronica;
	}

	public void setDoencaCronica(Boolean doencaCronica) {
		this.doencaCronica = doencaCronica;
	}

	public Boolean isUsaMedicaAtual() {
		return usaMedicaAtual;
	}

	public void setUsaMedicaAtual(Boolean usaMedicaAtual) {
		this.usaMedicaAtual = usaMedicaAtual;
	}

}
