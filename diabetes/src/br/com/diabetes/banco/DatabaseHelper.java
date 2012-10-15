package br.com.diabetes.banco;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

	public DatabaseHelper(Context context) {
		super(context, "diabetes.sqlite", null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE teste(" + "id INT AUTO_INCREMENT,"
				+ "nome VARCHAR(100)," + "descricao VARCHAR(200),"
				+ "primary key(id));");

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}

	public SQLiteDatabase getDatabase() {
		return this.getWritableDatabase();
	}

}
