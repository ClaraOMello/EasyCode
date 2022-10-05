package model;

public class Linguagem {
	private int id;
	private String nome;
	private String img;
	
	public Linguagem() {
		id = -1;
		nome = "";
		img = "";
	}
	public Linguagem(int id, String nome, String img) {
		setId(id);
		setNome(nome);
		setImg(img);
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public void setImg(String img) {
		this.img = img;
	}
	
	public int getId() {
		return id;
	}
	public String getNome() {
		return nome;
	}
	public String getImg() {
		return img;
	}
	
	@Override
	public String toString() {
		return "Linguagem: " + nome + "   Endereço imagem: " + img;
	}
	
	@Override
	public boolean equals(Object obj) {
		return (this.getId() == ((Linguagem) obj).getId());
	}

}
