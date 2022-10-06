package model;

public class Topico {
	private int id;
	private String nome;
	private int ling;
	private String conteudo;
	
	public Topico() {
		id = -1;
		nome = "";
		ling = 0;
		conteudo = "";
	}
	public Topico(int id, int ling, String nome, String conteudo) {
		setId(id);
		setNome(nome);
		setLing(ling);
		setConteudo(conteudo);
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public void setLing(int ling) {
		this.ling = ling;
	}
	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}
	
	public int getId() {
		return id;
	}
	public String getNome() {
		return nome;
	}
	public int getLing() {
		return ling;
	}
	public String getConteudo() {
		return conteudo;
	}
	
	@Override
	public String toString() {
		return "Nome: " + nome + "   Linguagem: " + ling + "   Conteúdo: " + conteudo;
	}
	
	@Override
	public boolean equals(Object obj) {
		return (this.getId() == ((Topico) obj).getId());
	}

}
