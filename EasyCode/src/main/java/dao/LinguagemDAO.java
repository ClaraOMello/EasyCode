package dao;

import model.Linguagem;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class LinguagemDAO extends DAO {
	public LinguagemDAO() {
		super();
		conectar();
	}

	public void finalize() {
		close();
	}

	public boolean insert(Linguagem ling) {
		boolean status = false;
		try {
			String sql = "INSERT INTO linguagem (id, nome, img) "
					+ "VALUES (" + ling.getId() + ", '" + ling.getNome() + "', '"
					+ ling.getImg() + "');";
			PreparedStatement st = conexao.prepareStatement(sql);
			st.executeUpdate();
			st.close();
			status = true;
		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
		return status;
	}

	public Linguagem get(int id) {
		Linguagem ling = null;

		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM linguagem WHERE id=" + id;
			ResultSet rs = st.executeQuery(sql);
			if (rs.next()) {
				ling = new Linguagem(rs.getInt("id"), rs.getString("nome"), rs.getString("img"));
			}
			st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return ling;
	}

	public List<Linguagem> get() {
		return get("");
	}

	public List<Linguagem> getOrderById() {
		return get("id");
	}

	public List<Linguagem> getOrderByNome() {
		return get("nome");
	}

	private List<Linguagem> get(String orderBy) {
		List<Linguagem> lings = new ArrayList<Linguagem>();

		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM linguagem" + ((orderBy.trim().length() == 0) ? "" : (" ORDER BY " + orderBy));
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				Linguagem l = new Linguagem(rs.getInt("id"), rs.getString("nome"), rs.getString("img"));
				lings.add(l);
			}
			st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return lings;
	}

	public boolean update(Linguagem linguagem) {
		boolean status = false;
		try {
			String sql = "UPDATE linguagem SET nome = '" + linguagem.getNome() + "', "
					+ "img = '" + linguagem.getImg() + "'"
					+ "WHERE id = " + linguagem.getId();
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
			st.executeUpdate("DELETE FROM linguagem WHERE id = " + id);
			st.close();
			status = true;
		} catch (SQLException u) {
			System.out.println("Falha no delete");
			throw new RuntimeException(u);
		}
		return status;
	}
}