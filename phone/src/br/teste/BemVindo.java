package br.teste;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class BemVindo extends Activity {

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.bemvindo);
		
		final Button botao = (Button) findViewById(R.id.button1);
		
		botao.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
				
			}
		});
		
	}
}
