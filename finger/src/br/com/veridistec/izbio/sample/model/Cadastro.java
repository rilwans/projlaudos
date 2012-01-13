package br.com.veridistec.izbio.sample.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "cadastros")
public class Cadastro {

	private Long id;
	private String nome;
	private byte[] template1;
	private byte[] template2;
	private byte[] template3;
	private byte[] template4;

	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Lob
	public byte[] getTemplate1() {
		return template1;
	}

	public void setTemplate1(byte[] template1) {
		this.template1 = template1;
	}

	@Lob
	public byte[] getTemplate2() {
		return template2;
	}

	public void setTemplate2(byte[] template2) {
		this.template2 = template2;
	}

	@Lob
	public byte[] getTemplate3() {
		return template3;
	}

	public void setTemplate3(byte[] template3) {
		this.template3 = template3;
	}

	@Lob
	public byte[] getTemplate4() {
		return template4;
	}

	public void setTemplate4(byte[] template4) {
		this.template4 = template4;
	}
}
