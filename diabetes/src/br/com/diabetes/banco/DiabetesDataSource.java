package br.com.diabetes.banco;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class DiabetesDataSource {

	private SQLiteDatabase db;
	private DatabaseHelper helper;
	
	
	public DiabetesDataSource(Context context){
		helper = new DatabaseHelper(context);
		db = helper.getDatabase();
	}
}
