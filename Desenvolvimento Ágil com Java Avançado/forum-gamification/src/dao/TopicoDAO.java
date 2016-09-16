package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Topico;
import model.Usuario;
import util.ConnectionFactory;

public class TopicoDAO {

	public List<Topico> todosTopicos(){
		List<Topico> topicos = new ArrayList<>();
		
		try(Connection conn = ConnectionFactory.getConnection()){
			String sql = "SELECT * FROM topico t JOIN usuario u ON t.login = u.login";
			
			PreparedStatement statement = conn.prepareStatement(sql);
			
			ResultSet resultSet = statement.executeQuery();
			
			while(resultSet.next()){
				Topico topico = new Topico();
				topico.setId_topico(resultSet.getInt("id_topico"));
				topico.setTitulo(resultSet.getString("titulo"));
				topico.setConteudo(resultSet.getString("conteudo"));
				
				Usuario usuario = new Usuario();
				usuario.setLogin(resultSet.getString("login"));
				usuario.setEmail(resultSet.getString("email"));
				usuario.setNome(resultSet.getString("nome"));
				usuario.setSenha(resultSet.getString("senha"));
				usuario.setPontos(resultSet.getInt("pontos"));
				
				topico.setUsuario(usuario);
				topicos.add(topico);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return topicos;
	}
	
	public Topico buscarTopico(int topicoId){
		Topico topico = new Topico();
		
		try(Connection conn = ConnectionFactory.getConnection()){
			String sql = "SELECT * FROM topico t JOIN usuario u ON t.login = u.login where t.id_topico = ?";
			
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, topicoId);
			
			ResultSet resultSet = statement.executeQuery();
			
			while(resultSet.next()){
				topico.setId_topico(resultSet.getInt("id_topico"));
				topico.setTitulo(resultSet.getString("titulo"));
				topico.setConteudo(resultSet.getString("conteudo"));
				
				Usuario usuario = new Usuario();
				usuario.setLogin(resultSet.getString("login"));
				usuario.setEmail(resultSet.getString("email"));
				usuario.setNome(resultSet.getString("nome"));
				usuario.setSenha(resultSet.getString("senha"));
				usuario.setPontos(resultSet.getInt("pontos"));
				
				topico.setUsuario(usuario);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return topico;
	}
	
	public void inserirNovo(Topico topico){
		try(Connection conn = ConnectionFactory.getConnection()){
			String sql = "INSERT INTO topico (titulo, conteudo, login) VALUES (?, ?, ?)";
			
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, topico.getTitulo());
			statement.setString(2, topico.getConteudo());
			statement.setString(3, topico.getUsuario().getLogin());
			
			statement.executeUpdate();
			statement.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
