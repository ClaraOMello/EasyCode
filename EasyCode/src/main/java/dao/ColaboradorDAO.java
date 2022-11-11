package dao;

import model.Colaborador;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class ColaboradorDAO extends DAO {

	public ColaboradorDAO() {
		super();
		conectar();
	}
	
	public void finalize() {
		close();
	}
	
	public boolean insert(Colaborador colab) {
		boolean status = false;
		try {
			String sql = "INSERT INTO colaborador (id, adm, nome, email, senha) "
		               + "VALUES (" + "?" + ", " + "?" + ", "
		               + "?" + ", " + "?" + ", " + "?" + ");";
			PreparedStatement st = conexao.prepareStatement(sql);
			st.setInt(1, colab.getId());
			st.setBoolean(2, colab.getAdm());
			st.setString(3, colab.getNome());
			st.setString(4, colab.getEmail());
			st.setString(5, colab.getSenha());
			st.executeUpdate();
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	public int getLastId() {
	    int lastId = -1;
	    try {
            String sql = "SELECT MAX(id) as max FROM colaborador";
            Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = st.executeQuery(sql);
            if(rs.next()) lastId = rs.getInt("max");
            st.close();
        } catch (SQLException u) {  
            throw new RuntimeException(u);
        }
	    return lastId;
	}

	
	public Colaborador get(int id) {
		Colaborador colab = null;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM colaborador WHERE id="+id;
			ResultSet rs = st.executeQuery(sql);	
	        if(rs.next()){            
	        	 colab = new Colaborador(rs.getInt("id"), rs.getBoolean("adm"), rs.getString("nome"), rs.getString("email"), 
	                				   rs.getString("senha"));
	        	 colab.setDescricao(rs.getString("descricao"));
	        }
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return colab;
	}
	public Colaborador getByNome(String nome) {
        Colaborador colab = null;
        
        try {
            Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            String sql = "SELECT * FROM colaborador WHERE nome='"+nome+"'";
            ResultSet rs = st.executeQuery(sql);    
            if(rs.next()){            
                 colab = new Colaborador(rs.getInt("id"), rs.getBoolean("adm"), rs.getString("nome"), rs.getString("email"), 
                                       rs.getString("senha"));

                 colab.setDescricao(rs.getString("descricao"));
            }
            st.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return colab;
    }
    
	
	public List<Colaborador> get() {
		return get("");
	}

	
	public List<Colaborador> getOrderById() {
		return get("id");		
	}
	
	
	public List<Colaborador> getOrderByNome() {
		return get("nome");		
	}
	
	
	public List<Colaborador> getOrderByEmail() {
		return get("email");		
	}
	
	
	private List<Colaborador> get(String orderBy) {
		List<Colaborador> colabs = new ArrayList<Colaborador>();
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM colaborador" + ((orderBy.trim().length() == 0) ? "" : (" ORDER BY " + orderBy));
			ResultSet rs = st.executeQuery(sql);	           
	        while(rs.next()) {	            	
	        	Colaborador c = new Colaborador(rs.getInt("id"), rs.getBoolean("adm"), rs.getString("nome"), rs.getString("email"), 
     				   rs.getString("senha"));
	        	c.setDescricao(rs.getString("descricao"));
	            colabs.add(c);
	        }
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return colabs;
	}
	
	
	public boolean update(Colaborador colaborador) {
		boolean status = false;
		try {  
			String sql = "UPDATE colaborador SET adm = '" + colaborador.getAdm() + "', "
					   + "nome = '" + colaborador.getNome() + "', " 
					   + "email = '" + colaborador.getEmail() + "', " 
					   + "senha = '" + colaborador.getSenha() + "', " 
		               + "descricao = '" + colaborador.getDescricao() 
					   + "' WHERE id = " + colaborador.getId();
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
			st.executeUpdate("DELETE FROM colaborador WHERE id = " + id);
			st.close();
			status = true;
		} catch (SQLException u) {
			System.out.println("Falha no delete");
			throw new RuntimeException(u);
		}
		return status;
	}
}