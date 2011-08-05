package br.teste;

import android.app.ListActivity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.SimpleCursorAdapter;

public class PhoneActivity extends ListActivity {

	private static final String TAG = "Teste phone";
	private Cursor cursor;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		final Intent i = new Intent(this, BemVindo.class);

		startActivity(i);

		Button botao = (Button) findViewById(R.id.botaoVoltar);

		botao.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Log.d(TAG, "Botao");
				startActivity(i);

			}
		});

		Button botaoinserir = (Button) findViewById(R.id.botaoinserir);

		botaoinserir.setOnClickListener(mInsertListener);

		try{
			
		
		cursor = this.getContentResolver().query(PhoneProvider.Teste.CONTENT_URI, null, null, null, null);

		
		ListAdapter adapter = new SimpleCursorAdapter(
		// O primeiro parametro eh o context.
				this,
				// O segundo, o layout de cada item.
				R.layout.list_item,
				// O terceiro parametro eh o cursor que contem os dados
				// a serem mostrados
				cursor,
				// o quarto parametro eh um array com as colunas do
				// cursor que serao mostradas
				new String[] { PhoneProvider.Teste.TEXT, PhoneProvider.Teste.TESTE_ID },
				// o quinto parametro eh um array (com o mesmo
				// tamanho do anterior) com os elementos que
				// receberao os dados.
				new int[] { R.id.text, R.id.textID });

		setListAdapter(adapter);
		}
		catch (Exception e) {
			Log.d(TAG, e.getMessage());
		}
		
		
		Button botaoremover = (Button) findViewById(R.id.botaoexcluir);

		botaoinserir.setOnClickListener(mDeleteListener);

	}

	// Definindo um OnClickListener para o botão "Inserir"
	private OnClickListener mInsertListener = new OnClickListener() {
		public void onClick(View v) {
			EditText editBox = (EditText) findViewById(R.id.txtInsere);
			addNote(editBox.getText().toString());
			editBox.setText("");
		}
	};
	
	// Definindo um OnClickListener para o botão "excluir"
		private OnClickListener mDeleteListener = new OnClickListener() {
			public void onClick(View v) {
				Log.d(TAG,"entrou");
				EditText editBox = (EditText) findViewById(R.id.textID);
				remNote(editBox.getText().toString());
			}
		};

	protected void addNote(String text) {

		try {
			ContentValues values = new ContentValues();
			values.put(PhoneProvider.Teste.TEXT, text);

			getContentResolver().insert(PhoneProvider.Teste.CONTENT_URI, values);
		} catch (Exception e) {
			Log.e(TAG, e.getMessage());
		}

	}
	
	protected void remNote(String text) {

		try {
			String[] values = null;
			String comDelete = "_id="+text.trim();

			Log.d(TAG,text);
			
			getContentResolver().delete(PhoneProvider.Teste.CONTENT_URI, comDelete, values);
		} catch (Exception e) {
			Log.e(TAG, e.getMessage());
		}

	}

}