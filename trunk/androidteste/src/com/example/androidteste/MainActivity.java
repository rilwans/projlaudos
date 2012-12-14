package com.example.androidteste;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;

public class MainActivity extends Activity {

	 @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_main);

	        /** Chamando o Processo AsyncTask
	         *  Os parametros passado no método execute, vão para o método
	         *  doInBackground(Integer... params).
	         */
	        Processo processo = new Processo(this);
	        processo.execute();
	    }

	    public class Processo extends AsyncTask<Integer, String, Integer> {

	        private ProgressDialog progress;
	        private Context context;

	        public Processo(Context context) {
	            this.context = context;
	        }

	        @Override
	        protected void onPreExecute() {
	            //Cria novo um ProgressDialogo e exibe
	            progress = new ProgressDialog(context);
	            progress.setMessage("Aguarde...");
	            progress.show();
	        }

	        @Override
	        protected Integer doInBackground(Integer... paramss) {
	            /*
	        	for (int i = 0; i < paramss.length; i++) {
	                try {
	                    //Simula processo...
	                    Thread.sleep(paramss[i]);
	                    //Atualiza a interface através do onProgressUpdate
	                    publishProgress(i + "...");
	                } catch (Exception e) {
	                    e.printStackTrace();
	                }
	            }*/

	        	try {
	    			// defaultHttpClient
	    			DefaultHttpClient httpClient = new DefaultHttpClient();
	    			HttpPost httpPost = new HttpPost("http://www.foconaoferta.com.br/coletivo/xml/ofertas.xml");

	    			HttpResponse httpResponse = httpClient.execute(httpPost);
	    			HttpEntity httpEntity = httpResponse.getEntity();
	    			String xml = EntityUtils.toString(httpEntity);

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
	            //Cancela progressDialogo
	            progress.dismiss();
	        }

	        @Override
	        protected void onProgressUpdate(String... values) {
	            //Atualiza mensagem
	            progress.setMessage(values[0]);
	        }
	    }
}
