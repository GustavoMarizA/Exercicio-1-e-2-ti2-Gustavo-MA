package maven.demo;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;

public class DAO {
	
	private Connection connection = null;
	
	public DAO() {
		connect();
	}
	
	public void connect() {
		String driverName = "org.postgresql.Driver";                    
		String serverName = "localhost";
		String mydatabase = "Exercicio2";
		int porta = 5432;
		String url = "jdbc:postgresql://" + serverName + ":" + porta +"/" + mydatabase;
		String username = "ti2cc";
		String password = "ti@cc";

		try {
			Class.forName(driverName);
			connection = DriverManager.getConnection(url, username, password);
			System.out.println("Conexão efetuada com o postgres!");
		} catch (ClassNotFoundException error) { 
			System.err.println("Erro na conexão com o postgres (Erro no driver) " + error.getMessage());
		} catch (SQLException error) {
			System.err.println("Erro na conexão com o postgres  " + error.getMessage());
		}
	}
	
	public void disconnect() {
		try {
			connection.close();
		}catch(SQLException error) {
			System.out.println(error.getMessage());
		}
	}

	//Funções de manipulação do usuário:
	
	public void insertUser(Usuario user) {
		try {
			Statement stat = connection.createStatement();
			String sql = "INSERT INTO usuario (id, nome, login, senha) VALUES (" +
						user.getUserId() + ", '" + user.getUserName() + "', '" +
						user.getUserLogin() + "', '" + user.getUserSenha() + "');";
		
			stat.executeUpdate(sql);
			stat.close();
		}catch(SQLException error) {}
	}
	
	public void deleteUser(int id) {
		try {
			Statement stat = connection.createStatement();
			String sql = "DELETE FROM usuario WHERE id = " + id;
			stat.executeUpdate(sql);
			stat.close();
		}catch(SQLException error) {
			System.out.println(error.getMessage());
		}
	}

	public void displayUsers() {
		List<Usuario> users = new ArrayList<Usuario>(); 
		
		try {
			Statement stat = connection.createStatement();
			String sql = "SELECT * FROM usuario";
			ResultSet results;
			results = stat.executeQuery(sql);
			
			while(results.next()) {
				Usuario newUser = new Usuario(results.getInt("id"), 
						results.getString("nome"),
						results.getString("login"),
						results.getString("senha")
						);
				users.add(newUser);
			}
		}catch(SQLException error) {}
	
		for(int i = 0; i < users.size(); i++) {
			System.out.println("" + i + ":" +
					"id: " + users.get(i).getUserId() + ", " +
					"nome: " + users.get(i).getUserName() + ", " +
					"login: " + users.get(i).getUserLogin() + ", " +
					"senha: " + users.get(i).getUserSenha()
					);
		}
	}
}
