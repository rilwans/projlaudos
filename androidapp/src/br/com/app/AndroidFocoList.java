package br.com.app;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

public class AndroidFocoList extends ListActivity {
	static final String URL = "http://www.foconaoferta.com.br/coletivo/xml/ofertas.xml";
	// XML node keys
	static final String OFERTA = "oferta"; // parent node
	static final String SITE = "site";
	static final String TITULO = "titulo";
	static final String HREF = "href";
	static final String IMAGEM = "imagem";
	static final String PRECOTOTAL = "precototal";
	static final String PRECODESCONTO = "precodesconto";
	static final String DESCONTO = "desconto";
	static final String QTDCOMPRADOS = "qtdcomprados";
	static final String ATIVA = "ativa";

	static boolean atualiza = true;

	private ListView list;

	MyAdapter meuAdapter;

	private static ArrayList<Oferta> ofertas;

	private ArrayList<String> ordenacao = new ArrayList<String>();

	private ArrayList<Oferta> ofertasOrdenadas = new ArrayList<Oferta>();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.main);

		list = (ListView) findViewById(android.R.id.list);

		Spinner spOrdecacao = (Spinner) findViewById(R.id.spOrdenacao);

		ordenacao.add("Aleatorio");
		ordenacao.add("Menor Preço");
		ordenacao.add("Maior Preço");
		ordenacao.add("Maior Desconto");
		ordenacao.add("Menor Desconto");
		ordenacao.add("Mais Comprados");
		ordenacao.add("Menos Comprados");
		ordenacao.add("Ativos");
		ordenacao.add("Sites");

		@SuppressWarnings("rawtypes")
		ArrayAdapter adp = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, ordenacao);

		spOrdecacao.setAdapter(adp);

		if (atualiza) {
			atualiza = false;
			ofertas = new ArrayList<Oferta>();
			XMLParser parser = new XMLParser();
			String xml = parser.getXmlFromUrl(URL); // getting XML
			Document doc = parser.getDomElement(xml); // getting DOM element

			NodeList nl = doc.getElementsByTagName(OFERTA);

			// looping through all item nodes <item>
			for (int i = 0; i < nl.getLength(); i++) {

				// creating new HashMap

				Element e = (Element) nl.item(i);
				// adding each child node to HashMap key => value
				Oferta oferta = new Oferta();
				oferta.setATIVA(parser.getValue(e, ATIVA));
				oferta.setDESCONTO(parser.getValue(e, DESCONTO));
				oferta.setHREF(parser.getValue(e, HREF));
				oferta.setIMAGEM(parser.getValue(e, IMAGEM));
				oferta.setPRECODESCONTO(parser.getValue(e, PRECODESCONTO));
				oferta.setPRECOTOTAL(parser.getValue(e, PRECOTOTAL));
				oferta.setQTDCOMPRADOS(parser.getValue(e, QTDCOMPRADOS));
				oferta.setSITE(parser.getValue(e, SITE).trim());
				oferta.setTITULO(parser.getValue(e, TITULO));

				ofertas.add(oferta);
			}

		} // menuItems = getMap();

		meuAdapter = new MyAdapter(this, getMap(0), R.layout.list_view, new String[] { TITULO, PRECODESCONTO, QTDCOMPRADOS, ATIVA, SITE },
				new int[] { R.id.titulo, R.id.precodesconto, R.id.qtdcomprados, R.id.ativa, R.id.site });

		list.setAdapter(meuAdapter);

		list.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapter, View v, int position, long arg3) {

				Intent i = new Intent(AndroidFocoList.this, Detalhar.class);


				Oferta oferta = ofertasOrdenadas.get(position);

				Detalhar.oferta = oferta;

				//Toast.makeText(AndroidFocoList.this, oferta.getTITULO(), Toast.LENGTH_SHORT).show();

				startActivity(i);

			}
		});

		spOrdecacao.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView adapter, View v, int i, long lng) {
				Toast.makeText(AndroidFocoList.this, "Atualizado com Sucesso!", Toast.LENGTH_SHORT).show();
				criaAdapter(i);

				meuAdapter.notifyDataSetChanged();
				list.setAdapter(meuAdapter);
			}

			@Override
			public void onNothingSelected(AdapterView arg0) {
				// do something else
			}
		});

	}

	public void criaAdapter(int chave) {
		meuAdapter = new MyAdapter(this, getMap(chave), R.layout.list_view,
				new String[] { TITULO, PRECODESCONTO, QTDCOMPRADOS, ATIVA, SITE }, new int[] { R.id.titulo, R.id.precodesconto,
						R.id.qtdcomprados, R.id.ativa, R.id.site });
	}

	public ArrayList<HashMap<String, String>> getMap(int chave) {

		ArrayList<HashMap<String, String>> maps = new ArrayList<HashMap<String, String>>();

		ofertasOrdenadas = ofertas;

		if (chave == 1) {

			Collections.sort(ofertasOrdenadas, new Comparator<Oferta>() {
				public int compare(final Oferta o1, final Oferta o2) {
					Oferta p1 = (Oferta) o1;
					Oferta p2 = (Oferta) o2;
					return p1.getVlDesconto() < p2.getVlDesconto() ? -1 : p1.getVlDesconto() > p2.getVlDesconto() ? +1 : 0;
				}
			});
		}
		if (chave == 2) {

			Collections.sort(ofertasOrdenadas, new Comparator<Oferta>() {
				public int compare(final Oferta o1, final Oferta o2) {
					Oferta p1 = (Oferta) o1;
					Oferta p2 = (Oferta) o2;
					return p1.getVlDesconto() > p2.getVlDesconto() ? -1 : p1.getVlDesconto() < p2.getVlDesconto() ? +1 : 0;
				}
			});
		}
		if (chave == 3) {

			Collections.sort(ofertasOrdenadas, new Comparator<Oferta>() {
				public int compare(final Oferta o1, final Oferta o2) {
					Oferta p1 = (Oferta) o1;
					Oferta p2 = (Oferta) o2;
					return p1.getVlPercDesconto() < p2.getVlPercDesconto() ? -1 : p1.getVlPercDesconto() > p2.getVlPercDesconto() ? +1 : 0;
				}
			});
		}
		if (chave == 4) {

			Collections.sort(ofertasOrdenadas, new Comparator<Oferta>() {
				public int compare(final Oferta o1, final Oferta o2) {
					Oferta p1 = (Oferta) o1;
					Oferta p2 = (Oferta) o2;
					return p1.getVlPercDesconto() > p2.getVlPercDesconto() ? -1 : p1.getVlPercDesconto() < p2.getVlPercDesconto() ? +1 : 0;
				}
			});
		}
		if (chave == 5) {

			Collections.sort(ofertasOrdenadas, new Comparator<Oferta>() {
				public int compare(final Oferta o1, final Oferta o2) {
					Oferta p1 = (Oferta) o1;
					Oferta p2 = (Oferta) o2;
					return Integer.parseInt(p1.getQTDCOMPRADOS()) > Integer.parseInt(p2.getQTDCOMPRADOS()) ? -1 : Integer.parseInt(p1
							.getQTDCOMPRADOS()) < Integer.parseInt(p2.getQTDCOMPRADOS()) ? +1 : 0;
				}
			});
		}

		if (chave == 6) {

			Collections.sort(ofertasOrdenadas, new Comparator<Oferta>() {
				public int compare(final Oferta o1, final Oferta o2) {
					Oferta p1 = (Oferta) o1;
					Oferta p2 = (Oferta) o2;
					return Integer.parseInt(p1.getQTDCOMPRADOS()) < Integer.parseInt(p2.getQTDCOMPRADOS()) ? -1 : Integer.parseInt(p1
							.getQTDCOMPRADOS()) > Integer.parseInt(p2.getQTDCOMPRADOS()) ? +1 : 0;
				}
			});
		}

		if (chave == 7) {

			ofertasOrdenadas = new ArrayList<Oferta>();
			for (Oferta o : ofertas) {
				if (o.getOfertaAtiva()) {
					ofertasOrdenadas.add(o);
				}
			}

		}

		if (chave == 8) {

			Collections.sort(ofertasOrdenadas, new Comparator<Oferta>() {
				public int compare(final Oferta o1, final Oferta o2) {
					Oferta p1 = (Oferta) o1;
					Oferta p2 = (Oferta) o2;
					return Integer.parseInt(p1.getSITE()) < Integer.parseInt(p2.getSITE()) ? -1 : Integer.parseInt(p1.getSITE()) > Integer
							.parseInt(p2.getSITE()) ? +1 : 0;
				}
			});
		}

		for (Oferta o : ofertasOrdenadas) {
			HashMap<String, String> map = new HashMap<String, String>();

			map.put(SITE, o.getSITE());
			map.put(TITULO, o.getTITULO());
			map.put(HREF, o.getHREF());
			map.put(IMAGEM, o.getIMAGEM());
			map.put(PRECOTOTAL, "R$ " + o.getPRECOTOTAL());
			map.put(PRECODESCONTO, "R$ " + o.getPRECODESCONTO());
			map.put(DESCONTO, o.getDESCONTO());
			map.put(QTDCOMPRADOS, o.getQTDCOMPRADOS());
			map.put(ATIVA, o.getATIVA());

			// adding HashList to ArrayList

			maps.add(map);

		}

		return maps;
	}
}
