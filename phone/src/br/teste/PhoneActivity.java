package br.teste;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PhoneActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		final Intent i = new Intent(this, BemVindo.class);

		startActivity(i);

		final Button botao = (Button) findViewById(R.id.botaoVoltar);

		botao.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				
				startActivity(i);

			}
		});

	}
}