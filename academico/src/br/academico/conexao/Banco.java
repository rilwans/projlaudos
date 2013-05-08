package br.academico.conexao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;

public class Banco {

	private Connection conexao; // Conexão com o servidor de banco de dados
	private Statement statement; // Declaração para executar os comandos



	public Banco() throws Exception{
		String url = "jdbc:mysql://127.0.0.1/academico?user=root&password=root";
		String usr = "root";
		String pwd = "root";

		// Carregar o driver
		Class.forName("com.mysql.jdbc.Driver");

		// Conectar com o servidor de banco de dados
		conexao = DriverManager.getConnection(url, usr, pwd);

		statement = conexao.createStatement();


	}

	public Connection getConexao() {
		return conexao;
	}


	public Statement getStatement() {
		return statement;
	}


}
