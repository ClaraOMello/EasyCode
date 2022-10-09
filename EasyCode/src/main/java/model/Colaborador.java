package model;

public class Colaborador {
	private int id;
	private boolean adm;
	private String nome;
	private String email;
	private String senha;
	
	public Colaborador() {
		id = -1;
		email = "";
		senha = "";
		nome = "";
		adm = false;
	}
	public Colaborador(int id, boolean adm, String nome, String email, String senha) {
		setId(id);
		setNome(nome);
		setEmail(email);
		setSenha(senha);
		setAdm(adm);
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public void setAdm(boolean adm) {
		this.adm = adm;
	}
	
	public int getId() {
		return id;
	}
	public String getEmail() {
		return email;
	}
	public String getSenha() {
		return senha;
	}
	public String getNome() {
		return nome;
	}
	public boolean getAdm() {
		return adm;
	}
	
	@Override
	public String toString() {
		return "Nome: " + nome + "   Email: " + email + "   Adm: " + adm;
	}
	
	@Override
	public boolean equals(Object obj) {
		return (this.getId() == ((Colaborador) obj).getId());
	}

}
