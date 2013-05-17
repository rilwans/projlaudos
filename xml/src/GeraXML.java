import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class GeraXML {

	public static void main(String[] args) {

		Venda venda = new Venda();

		venda.setDtVenda(new Date());
		venda.setIdVenda(1);
		venda.setNmVendendor("Jose da Silva");
		venda.setVlTotal(1000);

		ItmVenda item1 = new ItmVenda();
		item1.setNmProduto("Cerveja Stela Artois CX");
		item1.setQtProduto(1);
		item1.setVlItmTotal((float) 50.00);
		item1.setVlProduto((float) 50.00);

		ItmVenda item2 = new ItmVenda();
		item2.setNmProduto("Picanha Argentina KG");
		item2.setQtProduto(1);
		item2.setVlItmTotal((float) 45.00);
		item2.setVlProduto((float) 45.00);

		ItmVenda item3 = new ItmVenda();
		item3.setNmProduto("Coca-cola LT");
		item3.setQtProduto(5);
		item3.setVlItmTotal((float) 25.00);
		item3.setVlProduto((float) 25.00);

		List<ItmVenda> items = new ArrayList<ItmVenda>();

		items.add(item1);
		items.add(item2);
		items.add(item3);

		venda.setItems(items);

		XStream xstream = new XStream(new DomDriver());

		xstream.alias("venda", Venda.class);
		xstream.alias("item", ItmVenda.class);

		String xml = xstream.toXML(venda);

		FileWriter xmlFile;
		try {
			xmlFile = new FileWriter("c:\\venda.xml", true);
			xmlFile.append(xml);
			xmlFile.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
