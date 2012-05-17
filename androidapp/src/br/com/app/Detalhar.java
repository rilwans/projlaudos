package br.com.app;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Detalhar extends Activity {

	private ArrayList<String> ordenacao = new ArrayList<String>();

	public static Oferta oferta;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.detalhar);

		ImageView imgAtivo = (ImageView) findViewById(R.id.imgAtiva);
		ImageView imgSite = (ImageView) findViewById(R.id.site);

		WebView wv = (WebView) findViewById(R.id.webImage);

		wv.loadUrl(oferta.getIMAGEM());

		TextView TituloDetalhe = (TextView) findViewById(R.id.TituloDetalhe);
		TextView ativo = (TextView) findViewById(R.id.detAtiva);
		TextView percentual = (TextView) findViewById(R.id.detPercentual);
		TextView precodesconto = (TextView) findViewById(R.id.detPrecoDesconto);
		TextView precototal = (TextView) findViewById(R.id.detPrecoTotal);
		TextView qtcomprados = (TextView) findViewById(R.id.detQtComprados);

		TituloDetalhe.setText(oferta.getTITULO());
		ativo.setText(oferta.getATIVA());
		percentual.setText(oferta.getDESCONTO() + " de Desconto");
		precodesconto.setText("Por R$ " + oferta.getPRECODESCONTO());
		precototal.setText("De " + oferta.getPRECOTOTAL());
		qtcomprados.setText(oferta.getQTDCOMPRADOS() + " já Compraram");

		if (oferta.getOfertaAtiva())
			imgAtivo.setImageDrawable(this.getApplicationContext().getResources().getDrawable(R.drawable.verde));
		else
			imgAtivo.setImageDrawable(this.getApplicationContext().getResources().getDrawable(R.drawable.amarelo));

		int site = Integer.parseInt(oferta.getSITE());

		switch (site) {
		case 1:
			imgSite.setImageDrawable(this.getApplicationContext().getResources().getDrawable(R.drawable.image1));
			break;
		case 3:
			imgSite.setImageDrawable(this.getApplicationContext().getResources().getDrawable(R.drawable.image3));
			break;
		case 5:
			imgSite.setImageDrawable(this.getApplicationContext().getResources().getDrawable(R.drawable.image5));
			break;
		case 8:
			imgSite.setImageDrawable(this.getApplicationContext().getResources().getDrawable(R.drawable.image8));
			break;
		case 9:
			imgSite.setImageDrawable(this.getApplicationContext().getResources().getDrawable(R.drawable.image9));
			break;
		case 10:
			imgSite.setImageDrawable(this.getApplicationContext().getResources().getDrawable(R.drawable.image10));
			break;
		case 11:
			imgSite.setImageDrawable(this.getApplicationContext().getResources().getDrawable(R.drawable.image11));
			break;
		case 12:
			imgSite.setImageDrawable(this.getApplicationContext().getResources().getDrawable(R.drawable.image12));
			break;
		case 13:
			imgSite.setImageDrawable(this.getApplicationContext().getResources().getDrawable(R.drawable.image13));
			break;
		case 14:
			imgSite.setImageDrawable(this.getApplicationContext().getResources().getDrawable(R.drawable.image14));
			break;
		case 16:
			imgSite.setImageDrawable(this.getApplicationContext().getResources().getDrawable(R.drawable.image16));
			break;
		case 17:
			imgSite.setImageDrawable(this.getApplicationContext().getResources().getDrawable(R.drawable.image17));
			break;
		case 20:
			imgSite.setImageDrawable(this.getApplicationContext().getResources().getDrawable(R.drawable.image20));
			break;
		case 22:
			imgSite.setImageDrawable(this.getApplicationContext().getResources().getDrawable(R.drawable.image22));
			break;
		case 23:
			imgSite.setImageDrawable(this.getApplicationContext().getResources().getDrawable(R.drawable.image23));
			break;
		case 24:
			imgSite.setImageDrawable(this.getApplicationContext().getResources().getDrawable(R.drawable.image24));
			break;
		case 25:
			imgSite.setImageDrawable(this.getApplicationContext().getResources().getDrawable(R.drawable.image25));
			break;
		default:
			imgSite.setImageDrawable(this.getApplicationContext().getResources().getDrawable(R.drawable.ic_launcher));
			break;
		}

		Button botaoAtualiza = (Button) findViewById(R.id.botaoVoltar);

		botaoAtualiza.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {

				Intent troca = new Intent(Detalhar.this, AndroidFocoList.class);
				AndroidFocoList.atualiza = false;
				Detalhar.this.startActivity(troca);
				Detalhar.this.finish();
			}
		});

		Button linkBtn = (Button) findViewById(R.id.botaoComprar);
		linkBtn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Uri uri = Uri.parse(oferta.getHREF());
				startActivity(new Intent(Intent.ACTION_VIEW, uri));
			}
		});

	}
}
