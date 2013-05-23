package br.academico.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Banco {

	private Connection conexao; // Conexão com o servidor de banco de dados
	private Statement statement; // Declaração para executar os comandos

	public Banco() {
		String url = "jdbc:mysql://127.0.0.1/academico";
		String usr = "root";
		String pwd = "";

		try {
			// Carregar o driver
			Class.forName("com.mysql.jdbc.Driver");

			// Conectar com o servidor de banco de dados
			conexao = DriverManager.getConnection(url, usr, pwd);

			statement = conexao.createStatement();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public Connection getConexao() {
		return conexao;
	}

	public Statement getStatement() {
		return statement;
	}

}
