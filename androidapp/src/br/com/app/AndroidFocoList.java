package br.com.app;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.ads.AdRequest;
import com.google.ads.AdSize;
import com.google.ads.AdView;

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

	private String xml;

	private int opcaoOrdena = 0;

	private Spinner spOrdecacao;

	MyAdapter meuAdapter;

	private static ArrayList<Oferta> ofertas;

	private ArrayList<String> ordenacao = new ArrayList<String>();

	private ArrayList<Oferta> ofertasOrdenadas = new ArrayList<Oferta>();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.main);

		AdView adView = new AdView(this, AdSize.BANNER, "a1500b6865dd03c");
		LinearLayout layout = (LinearLayout) findViewById(R.id.adLayout);

		FrameLayout.LayoutParams adsParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
				FrameLayout.LayoutParams.MATCH_PARENT, android.view.Gravity.CENTER_HORIZONTAL);

		layout.addView(adView, adsParams);

		AdRequest adRequest = new AdRequest();
		// adRequest.addTestDevice(AdRequest.LOGTAG);

		// Start loading the ad in the background.
		adView.loadAd(adRequest);

		ofertas = new ArrayList<Oferta>();
		
		list = (ListView) findViewById(android.R.id.list);

		spOrdecacao = (Spinner) findViewById(R.id.spOrdenacao);

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
			// runnable.run();
			Processo processo = new Processo(this);
			processo.execute();
		}
		

		list.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapter, View v, int position, long arg3) {

				Intent i = new Intent(AndroidFocoList.this, Detalhar.class);
				Oferta oferta = ofertasOrdenadas.get(position);

				Detalhar.oferta = oferta;

				// Toast.makeText(AndroidFocoList.this, oferta.getTITULO(),
				// Toast.LENGTH_SHORT).show();

				startActivity(i);

			}
		});

		Button atualizaBtn = (Button) findViewById(R.id.atualiza);
		atualizaBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Processo pro = new Processo(AndroidFocoList.this);
				pro.execute();
			}
		});

		spOrdecacao.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

			@SuppressWarnings("rawtypes")
			@Override
			public void onItemSelected(AdapterView adapter, View v, int i, long lng) {
				opcaoOrdena = i;
				criaAdapter(opcaoOrdena);

				meuAdapter.notifyDataSetChanged();
				list.setAdapter(meuAdapter);
			}

			@SuppressWarnings("rawtypes")
			@Override
			public void onNothingSelected(AdapterView arg0) {
				// do something else
			}
		});

	}

	public void criaAdapter(int chave) {
		meuAdapter = new MyAdapter(this, getMap(chave), R.layout.list_view, new String[] { TITULO, PRECODESCONTO,
				QTDCOMPRADOS, ATIVA, SITE }, new int[] { R.id.titulo, R.id.precodesconto, R.id.qtdcomprados,
				R.id.ativa, R.id.site });
	}

	public ArrayList<HashMap<String, String>> getMap(int chave) {

		ArrayList<HashMap<String, String>> maps = new ArrayList<HashMap<String, String>>();

		ofertasOrdenadas = ofertas;

		if (chave == 1) {

			Collections.sort(ofertasOrdenadas, new Comparator<Oferta>() {
				public int compare(final Oferta o1, final Oferta o2) {
					Oferta p1 = (Oferta) o1;
					Oferta p2 = (Oferta) o2;
					return p1.getVlDesconto() < p2.getVlDesconto() ? -1 : p1.getVlDesconto() > p2.getVlDesconto() ? +1
							: 0;
				}
			});
		}
		if (chave == 2) {

			Collections.sort(ofertasOrdenadas, new Comparator<Oferta>() {
				public int compare(final Oferta o1, final Oferta o2) {
					Oferta p1 = (Oferta) o1;
					Oferta p2 = (Oferta) o2;
					return p1.getVlDesconto() > p2.getVlDesconto() ? -1 : p1.getVlDesconto() < p2.getVlDesconto() ? +1
							: 0;
				}
			});
		}
		if (chave == 3) {

			Collections.sort(ofertasOrdenadas, new Comparator<Oferta>() {
				public int compare(final Oferta o1, final Oferta o2) {
					Oferta p1 = (Oferta) o1;
					Oferta p2 = (Oferta) o2;
					return p1.getVlPercDesconto() < p2.getVlPercDesconto() ? -1 : p1.getVlPercDesconto() > p2
							.getVlPercDesconto() ? +1 : 0;
				}
			});
		}
		if (chave == 4) {

			Collections.sort(ofertasOrdenadas, new Comparator<Oferta>() {
				public int compare(final Oferta o1, final Oferta o2) {
					Oferta p1 = (Oferta) o1;
					Oferta p2 = (Oferta) o2;
					return p1.getVlPercDesconto() > p2.getVlPercDesconto() ? -1 : p1.getVlPercDesconto() < p2
							.getVlPercDesconto() ? +1 : 0;
				}
			});
		}
		if (chave == 5) {

			Collections.sort(ofertasOrdenadas, new Comparator<Oferta>() {
				public int compare(final Oferta o1, final Oferta o2) {
					Oferta p1 = (Oferta) o1;
					Oferta p2 = (Oferta) o2;
					return Integer.parseInt(p1.getQTDCOMPRADOS()) > Integer.parseInt(p2.getQTDCOMPRADOS()) ? -1
							: Integer.parseInt(p1.getQTDCOMPRADOS()) < Integer.parseInt(p2.getQTDCOMPRADOS()) ? +1 : 0;
				}
			});
		}

		if (chave == 6) {

			Collections.sort(ofertasOrdenadas, new Comparator<Oferta>() {
				public int compare(final Oferta o1, final Oferta o2) {
					Oferta p1 = (Oferta) o1;
					Oferta p2 = (Oferta) o2;
					return Integer.parseInt(p1.getQTDCOMPRADOS()) < Integer.parseInt(p2.getQTDCOMPRADOS()) ? -1
							: Integer.parseInt(p1.getQTDCOMPRADOS()) > Integer.parseInt(p2.getQTDCOMPRADOS()) ? +1 : 0;
				}
			});
		}

		if (chave == 7) {

			Collections.sort(ofertasOrdenadas, new Comparator<Oferta>() {
				public int compare(final Oferta o1, final Oferta o2) {
					Oferta p1 = (Oferta) o1;
					Oferta p2 = (Oferta) o2;
					return p1.getVlAtivo() > p2.getVlAtivo() ? -1 : p1.getVlAtivo() < p2.getVlAtivo() ? +1 : 0;
				}
			});

		}

		if (chave == 8) {

			Collections.sort(ofertasOrdenadas, new Comparator<Oferta>() {
				public int compare(final Oferta o1, final Oferta o2) {
					Oferta p1 = (Oferta) o1;
					Oferta p2 = (Oferta) o2;
					return Integer.parseInt(p1.getSITE()) < Integer.parseInt(p2.getSITE()) ? -1 : Integer.parseInt(p1
							.getSITE()) > Integer.parseInt(p2.getSITE()) ? +1 : 0;
				}
			});
		}

		for (Oferta o : ofertasOrdenadas) {
			HashMap<String, String> map = new HashMap<String, String>();

			map.put(SITE, o.getSITE());
			map.put(TITULO, o.getTituloResumo());
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

	public class Processo extends AsyncTask<Integer, String, Integer> {

		private ProgressDialog progress;
		private Context context;

		public Processo(Context context) {
			this.context = context;
		}

		@Override
		protected void onPreExecute() {
			// Cria novo um ProgressDialogo e exibe
			progress = new ProgressDialog(context);
			progress.setMessage("Aguarde... Atualizando ofertas...");
			progress.show();
		}

		@Override
		protected Integer doInBackground(Integer... paramss) {

			try {

				DefaultHttpClient httpClient = new DefaultHttpClient();
				HttpPost httpPost = new HttpPost(URL);

				HttpResponse httpResponse = httpClient.execute(httpPost);
				HttpEntity httpEntity = httpResponse.getEntity();
				xml = EntityUtils.toString(httpEntity);

			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return 1;
		}

		@Override
		protected void onPostExecute(Integer result) {
			// Cancela progressDialogo
			XMLParser parser = new XMLParser();

			Document doc = parser.getDomElement(xml.replace("'", "")); // getting
																		// DOM
																		// element

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

				String qtd = parser.getValue(e, QTDCOMPRADOS);
				if (qtd.equals(""))
					oferta.setQTDCOMPRADOS("0");
				else
					oferta.setQTDCOMPRADOS(qtd);

				oferta.setSITE(parser.getValue(e, SITE).trim());
				oferta.setTITULO(parser.getValue(e, TITULO));

				ofertas.add(oferta);
			}

			criaAdapter(opcaoOrdena);

			meuAdapter.notifyDataSetChanged();
			list.setAdapter(meuAdapter);

			progress.dismiss();
		}

		@Override
		protected void onProgressUpdate(String... values) {
			// Atualiza mensagem
			progress.setMessage(values[0]);
		}
	}

}
