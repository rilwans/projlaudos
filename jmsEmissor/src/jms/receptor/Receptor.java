package jms.receptor;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.MessageConsumer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.InitialContext;

public class Receptor {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		InitialContext ic;
		try {
			ic = new InitialContext();

			// fábrica de conexões JMS
			ConnectionFactory factory = (ConnectionFactory) ic.lookup("jms/Factory");

			// fila
			Queue queue = (Queue) ic.lookup("jms/teste");

			// conexão JMS
			Connection connection = factory.createConnection();

			// sessão JMS
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

			// receptor de mensagens
			MessageConsumer receiver = session.createConsumer(queue,"(categoria='ioaoa')");

			// inicializa conexão
			connection.start();

			// recebendo
			TextMessage messagem = (TextMessage) receiver.receive(2000);


			while(messagem!=null){
				System.out.println(messagem.getText());
				messagem=(TextMessage) receiver.receive(2000);
			}
			// fechando
			receiver.close();
			session.close();
			connection.close();

			System.out.println("FIM");
			System.exit(0);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
