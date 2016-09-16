package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Comentario;
import model.Topico;
import model.Usuario;
import util.ConnectionFactory;

public class ComentarioDAO {

	public List<Comentario> comentariosPorTopico(int topicoId){
		List<Comentario> comentarios = new ArrayList<>();
		
		try(Connection conn = ConnectionFactory.getConnection()){
			String sql = "select c.id_comentario, c.comentario, c.login, u.nome, c.id_topico "
						+ "from comentario c "
						+ "join usuario u on u.login = c.login "
						+ "join topico t on t.id_topico = c.id_topico "
						+ "where c.id_topico = ?";
			
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, topicoId);
			
			ResultSet resultSet = statement.executeQuery();
			
			while(resultSet.next()){
				Comentario comentario = new Comentario();
				comentario.setId_comentario(resultSet.getInt("id_comentario"));
				comentario.setComentario(resultSet.getString("comentario"));
				
				Usuario usuario = new Usuario();
				usuario.setLogin(resultSet.getString("login"));
				usuario.setNome(resultSet.getString("nome"));
				comentario.setUsuario(usuario);
				
				Topico topico = new Topico();
				topico.setId_topico(resultSet.getInt("id_topico"));
				comentario.setTopico(topico);
				
				comentarios.add(comentario);
			}
			
			statement.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return comentarios;
	}
	
	public void inserirNovo(Comentario comentario){
		try(Connection conn = ConnectionFactory.getConnection()){
			String sql = "INSERT INTO comentario(comentario, login, id_topico) VALUES (?, ?, ?)";
			
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, comentario.getComentario());
			statement.setString(2, comentario.getUsuario().getLogin());
			statement.setInt(3, comentario.getTopico().getId_topico());
			
			statement.executeUpdate();
			statement.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
