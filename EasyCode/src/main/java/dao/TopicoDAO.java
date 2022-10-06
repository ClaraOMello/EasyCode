package dao;

import model.Topico;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class TopicoDAO extends DAO {
	public TopicoDAO() {
		super();
		conectar();
	}
	
	public void finalize() {
		close();
	}
	
	public boolean insert(Topico top) {
		boolean status = false;
		try {
			String sql = "INSERT INTO topico (id, ling, nome, conteudo) "
		               + "VALUES (" + top.getId() + ", '" + top.getLing() + "', "
		               + top.getNome() + ", " + top.getConteudo() + ");";
			PreparedStatement st = conexao.prepareStatement(sql);
			st.executeUpdate();
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	/////////meus
	public String getNomeLinguagem(int id) {
		String str = "Hello";
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT l.nome FROM linguagem as l WHERE l.id="+id;
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) str = rs.getString("nome");
	        st.close();
		} catch (Exception e) {
			System.err.println("getNomeLinguagem: " + e.getMessage());
		}
		return str;
	}
	
	public List<Topico> getTopicos(int ling) {
		List<Topico> topicos = new ArrayList<Topico>();
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT t.id, t.ling, t.nome, t.conteudo FROM topico as t WHERE t.ling="+ling;
			ResultSet rs = st.executeQuery(sql);	
			while(rs.next()) {	            	
	        	Topico t = new Topico(rs.getInt("id"), rs.getInt("ling"), rs.getString("nome"), 
	        			                rs.getString("conteudo"));
	            topicos.add(t);
	        }
	        st.close();
		} catch (Exception e) {
			System.err.println("getTopicos" + e.getMessage());
		}
		return topicos;
	}

	
	public Topico get(int id) {
		Topico top = null;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM topico WHERE id="+id;
			ResultSet rs = st.executeQuery(sql);	
	        if(rs.next()){            
	        	 top = new Topico(rs.getInt("id"), rs.getInt("ling"), rs.getString("nome"), 
	                				   rs.getString("conteudo"));
	        }
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return top;
	}
	
	
	public List<Topico> get() {
		return get("");
	}

	
	public List<Topico> getOrderById() {
		return get("id");		
	}
	
	
	public List<Topico> getOrderByNome() {
		return get("nome");		
	}
	
	
	private List<Topico> get(String orderBy) {
		List<Topico> topicos = new ArrayList<Topico>();
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM topico" + ((orderBy.trim().length() == 0) ? "" : (" ORDER BY " + orderBy));
			ResultSet rs = st.executeQuery(sql);	           
	        while(rs.next()) {	            	
	        	Topico t = new Topico(rs.getInt("id"), rs.getInt("ling"), rs.getString("nome"), 
	        			                rs.getString("conteudo"));
	            topicos.add(t);
	        }
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return topicos;
	}
	
	
	public boolean update(Topico top) {
		boolean status = false;
		try {  
			String sql = "UPDATE topico SET nome = '" + top.getNome() + "', "
					   + "conteudo = " + top.getConteudo() 
					   + " WHERE id = " + top.getId();
			PreparedStatement st = conexao.prepareStatement(sql);
			st.executeUpdate();
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	
	public boolean delete(int id) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			st.executeUpdate("DELETE FROM topico WHERE id = " + id);
			st.close();
			status = true;
		} catch (SQLException u) {
			System.out.println("Falha no delete");
			throw new RuntimeException(u);
		}
		return status;
	}
}