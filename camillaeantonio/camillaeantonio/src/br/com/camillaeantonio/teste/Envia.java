package br.com.camillaeantonio.teste;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

public class Envia {

	public static void main(String[] args) {
		SimpleEmail email = new SimpleEmail();
		
		try {
			email.setHostName("smtp.gmail.com"); // o servidor SMTP para envio do
												// e-mail
		
			email.addTo("paduamendes@gmail.com", "padua");

			email.setFrom("paduamendes@gmail.com", "Eu"); // remetente
			email.setSubject("Teste -> Email simples"); // assunto do e-mail
			email.setMsg("Teste de Email utilizando commons-email"); // conteudo
																		// do
																		// e-mail
			email.setAuthentication("paduamendes", "chupetitta");
			email.setSmtpPort(465);
			email.setSSL(true);
			email.setTLS(true);
			email.send();
		} catch (EmailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // destinatário
	}

}
