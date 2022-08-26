package maven.demo;

public class Usuario {
	private int id;
	private String nome;
	private String login;
	private String senha;
	
	public Usuario(int id, String nome, String login, String senha) {
		this.id = id;
		this.nome = nome;
		this.login = login;
		this.senha = senha;
	}
	
	public int getUserId() { return id; }
	public String getUserName() { return nome; }
	public String getUserLogin() { return login; }
	public String getUserSenha() { return senha; }
}
