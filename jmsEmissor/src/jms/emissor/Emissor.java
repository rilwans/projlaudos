package jms.emissor;

import java.util.Date;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.InitialContext;

public class Emissor {

	/**
	 * @param args
	 */
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		// TODO Auto-generated method stub
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

			// emissor de mensagens
			MessageProducer sender = session.createProducer(queue);

			// mensagem
			TextMessage message = session.createTextMessage();

			Date d = new Date();

			message.setStringProperty("categoria", "ioaoa");
			message.setText("padua " + d.toGMTString());

			// enviando
			sender.send(message);

			message.setText("padua " + d.toGMTString());
			sender.send(message);

			sender.close();
			session.close();
			connection.close();

			System.out.println("Mensagem Enviada");
			System.exit(0);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
