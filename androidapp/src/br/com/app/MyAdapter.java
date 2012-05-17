package br.com.app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SimpleAdapter;

public class MyAdapter extends SimpleAdapter {

	private Context context;
	private List<HashMap<String, String>> dados = new ArrayList<HashMap<String, String>>();

	@SuppressWarnings("unchecked")
	public MyAdapter(Context context, List<? extends Map<String, ?>> data, int resource, String[] from, int[] to) {
		super(context, data, resource, from, to);
		this.context = context;
		this.dados = (List<HashMap<String, String>>) data;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		View row = super.getView(position, convertView, parent);

		ImageView icon = (ImageView) row.findViewById(R.id.site);

		ImageView ativa = (ImageView) row.findViewById(R.id.ativa);

		icon.setAdjustViewBounds(true);

		HashMap<String, String> item = dados.get(position);

		int site = Integer.parseInt(item.get("site"));

		switch (site) {
		case 1:
			icon.setImageDrawable(context.getResources().getDrawable(R.drawable.image1));
			break;
		case 3:
			icon.setImageDrawable(context.getResources().getDrawable(R.drawable.image3));
			break;
		case 5:
			icon.setImageDrawable(context.getResources().getDrawable(R.drawable.image5));
			break;
		case 8:
			icon.setImageDrawable(context.getResources().getDrawable(R.drawable.image8));
			break;
		case 9:
			icon.setImageDrawable(context.getResources().getDrawable(R.drawable.image9));
			break;
		case 10:
			icon.setImageDrawable(context.getResources().getDrawable(R.drawable.image10));
			break;
		case 11:
			icon.setImageDrawable(context.getResources().getDrawable(R.drawable.image11));
			break;
		case 12:
			icon.setImageDrawable(context.getResources().getDrawable(R.drawable.image12));
			break;
		case 13:
			icon.setImageDrawable(context.getResources().getDrawable(R.drawable.image13));
			break;
		case 14:
			icon.setImageDrawable(context.getResources().getDrawable(R.drawable.image14));
			break;
		case 16:
			icon.setImageDrawable(context.getResources().getDrawable(R.drawable.image16));
			break;
		case 17:
			icon.setImageDrawable(context.getResources().getDrawable(R.drawable.image17));
			break;
		case 20:
			icon.setImageDrawable(context.getResources().getDrawable(R.drawable.image20));
			break;
		case 22:
			icon.setImageDrawable(context.getResources().getDrawable(R.drawable.image22));
			break;
		case 23:
			icon.setImageDrawable(context.getResources().getDrawable(R.drawable.image23));
			break;
		case 24:
			icon.setImageDrawable(context.getResources().getDrawable(R.drawable.image24));
			break;
		case 25:
			icon.setImageDrawable(context.getResources().getDrawable(R.drawable.image25));
			break;
		default:
			icon.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_launcher));
			break;
		}


		String ativado = item.get("ativa");

		if (ativado.equals("ativo"))
			ativa.setImageDrawable(context.getResources().getDrawable(R.drawable.verde));
		else
			ativa.setImageDrawable(context.getResources().getDrawable(R.drawable.amarelo));



		return (row);
	}
}
