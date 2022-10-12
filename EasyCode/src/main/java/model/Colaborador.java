package model;

import java.util.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;


public class Colaborador {
	private int id;
	private boolean adm;
	private String nome;
	private String email;
	private String senha;
	private LocalDateTime adesao;
	private String descricao;
	
	
	public Colaborador() {
		id = -1;
		email = "";
		senha = "";
		nome = "";
		adm = false;
		adesao = LocalDateTime.now();
		descricao = "";
	}
	public Colaborador(int id, boolean adm, String nome, String email, String senha) {
		setId(id);
		setNome(nome);
		setEmail(email);
		setSenha(senha);
		setAdm(adm);
		setAdesao(LocalDateTime.now());
		setDescricao("");
	}
	public Colaborador(int id, boolean adm, String nome, String email, String senha, Date adesao, String descricao) {
        setId(id);
        setNome(nome);
        setEmail(email);
        setSenha(senha);
        setAdm(adm);
        setAdesao(converterParaDateLocalDateTime(adesao));
        setDescricao(descricao);
    }
	
	private LocalDateTime converterParaDateLocalDateTime(Date data) {
	    LocalDateTime saida = data.toInstant().atZone( ZoneId.systemDefault() ).toLocalDateTime();
	    
        return saida;
    }
	
    private Date converterLocalDateTimeParaDate(LocalDateTime data) {
        Date saida = Date.from( data.atZone( ZoneId.systemDefault() ).toInstant() );
        
        return saida;
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
	public void setAdesao(LocalDateTime adesao) {
        this.adesao = adesao;
    }
	public void setDescricao(String descricao) {
        this.descricao = descricao;
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
	public LocalDateTime getAdesao() {
        return adesao;
    }
	public String getDescricao() {
        return descricao;
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
