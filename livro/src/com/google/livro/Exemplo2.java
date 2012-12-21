package com.google.livro;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class Exemplo2 extends Activity {
	public static final String tagLog ="Livro";
	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		TextView text = new TextView(this);
		text.setText("Carai");
		setContentView(text);
		Log.w(tagLog, "teste de log");
	}







}
