package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Usuario;
import util.ConnectionFactory;

public class UsuarioDAOImp implements UsuarioDAO {

	@Override
	public void inserir(Usuario u) {
		try(Connection conn = ConnectionFactory.getConnection()){
			String sql = "INSERT INTO usuario(login, email, nome, senha, pontos) VALUES (?, ?, ?, ?, ?)";
			
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, u.getLogin());
			statement.setString(2, u.getEmail());
			statement.setString(3, u.getNome());
			statement.setString(4, u.getSenha());
			statement.setInt(5, u.getPontos());
			
			statement.executeUpdate();
			statement.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Usuario recuperar(String login) {
		Usuario usuario = new Usuario();
		
		try(Connection conn = ConnectionFactory.getConnection()){
			String sql = "SELECT * FROM usuario WHERE login = ?;";
			
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, login);
			
			ResultSet resultSet = statement.executeQuery();
			
			while(resultSet.next()){
				usuario.setLogin(resultSet.getString("login"));
				usuario.setEmail(resultSet.getString("email"));
				usuario.setNome(resultSet.getString("nome"));
				usuario.setSenha(resultSet.getString("senha"));
				usuario.setPontos(resultSet.getInt("pontos"));
			}
			
			statement.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return usuario;
	}

	@Override
	public void adicionarPontos(String login, int pontos) {
		try(Connection conn = ConnectionFactory.getConnection()){
			String sql = "UPDATE usuario SET pontos = pontos + ? WHERE login = ?;";
			
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, pontos);
			statement.setString(2, login);
			
			statement.executeUpdate();
			statement.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Usuario> ranking() {
		List<Usuario> usuarios = new ArrayList<>();
		
		try(Connection conn = ConnectionFactory.getConnection()){
			String sql = "SELECT * FROM usuario ORDER BY pontos DESC;";
			
			PreparedStatement statement = conn.prepareStatement(sql);
			
			ResultSet resultSet = statement.executeQuery();
			
			while(resultSet.next()){
				Usuario usuario = new Usuario();
				
				usuario.setLogin(resultSet.getString("login"));
				usuario.setEmail(resultSet.getString("email"));
				usuario.setNome(resultSet.getString("nome"));
				usuario.setSenha(resultSet.getString("senha"));
				usuario.setPontos(resultSet.getInt("pontos"));
				
				usuarios.add(usuario);
			}
			
			statement.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return usuarios;
	}

}
