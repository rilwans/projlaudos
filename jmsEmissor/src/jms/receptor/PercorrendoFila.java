package jms.receptor;

import java.util.Enumeration;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Queue;
import javax.jms.QueueBrowser;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.InitialContext;

import com.sun.enterprise.module.RepositoryFactories;

public class PercorrendoFila {

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
			QueueBrowser queueBrowser = session.createBrowser(queue);

			@SuppressWarnings("unchecked")
			Enumeration<TextMessage> messages = queueBrowser.getEnumeration();

			int count = 1;

			while (messages.hasMoreElements()) {
				TextMessage message = messages.nextElement();
				System.out.println(count + ": " + message.getText());
				count++;
			}

			queueBrowser.close();
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
