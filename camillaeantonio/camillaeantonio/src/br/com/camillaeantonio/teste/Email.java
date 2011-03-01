package br.com.camillaeantonio.teste;

import java.io.IOException;
import java.io.PrintStream;

import sun.net.smtp.SmtpClient;

public class Email {
	private String origem = null;
	private String destino = null;
	private String smtp = null;
	private String assunto = null;
	private String mensagem = null;

	public void sendEmail() {

		try {
			SmtpClient client = new SmtpClient(smtp);
			client.from(getOrigem());
			client.to(getDestino());

			PrintStream msg = client.startMessage();
			msg.println("to:" + getOrigem());
			msg.println("Subject:" + getAssunto());
			msg.print("\r\n");
			msg.println(getMensagem());
			client.closeServer();
		} catch (IOException e) {
			System.out.println("error" + e);
		}

	}

	public String getOrigem() {
		return origem;
	}

	public void setOrigem(String valor) {
		origem = valor;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String valor) {
		destino = valor;
	}

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String valor) {
		assunto = valor;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String valor) {
		mensagem = valor;
	}

	public String getSmtp() {
		return smtp;
	}

	public void setSmtp(String valor) {
		smtp = valor;
	}
}
