package br.com.camillaeantonio.teste;

public class Teste {
	public static void main(String[] args) {

		try {

			Email MyEmail = new Email();
			MyEmail.setOrigem("paduamendes@gmail.com");
			MyEmail.setDestino("paduamendes@gmail.com");
			MyEmail.setAssunto("teste de e-mail");
			MyEmail.setMensagem("Testando ...\r\n Testando ... \r\n Testando ...");
			MyEmail.setSmtp("smtp.gmail.com");

			MyEmail.sendEmail();
			System.out.println("Foi");
			System.exit(0);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
