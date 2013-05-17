import java.io.BufferedReader;
import java.io.FileReader;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class XmlToJava {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		XStream xstream = new XStream(new DomDriver());

		xstream.alias("venda", Venda.class);
		xstream.alias("item", ItmVenda.class);

		String xml = "";

		FileReader filereader;
		try {
			filereader = new FileReader("c:\\venda.xml");

			@SuppressWarnings("resource")
			BufferedReader leitor = new BufferedReader(filereader);
			String linha="";
			while ((linha= leitor.readLine()) != null) {
				xml += linha;
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Venda venda2 = (Venda) xstream.fromXML(xml);

		System.out.println(venda2.getNmVendendor());
		System.out.println(venda2.getItems().get(0).getNmProduto());

	}

}
