package teste;

import javax.xml.ws.Endpoint;

public class PublicaService {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ConvertServer service = new ConvertServer();
		Endpoint endpoint = Endpoint.publish("http://localhost:8081/hello",
				service);
	}

}
