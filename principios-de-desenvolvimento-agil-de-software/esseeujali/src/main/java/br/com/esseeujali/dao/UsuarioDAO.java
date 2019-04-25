package br.com.esseeujali.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import br.com.esseeujali.exceptions.UsuarioNaoEncontradoException;
import br.com.esseeujali.models.Livro;
import br.com.esseeujali.models.Usuario;
import br.com.esseeujali.models.rest.Login;

public class UsuarioDAO {

	private EntityManagerFactory factory;

	public UsuarioDAO(EntityManagerFactory factory) {
		this.factory = factory;
	}
	
	public Usuario salvar(Usuario usuario) {
		EntityManager manager = factory.createEntityManager();
		manager.getTransaction().begin();
		
		manager.persist(usuario);
		
		manager.getTransaction().commit();
		manager.close();
		
		return usuario;
	}
	
	public Usuario buscaLogin(Login login) throws UsuarioNaoEncontradoException {
		EntityManager manager = factory.createEntityManager();
		
		TypedQuery<Usuario> query = manager.createQuery("FROM Usuario u LEFT JOIN FETCH u.livrosLidos WHERE u.usuario=:usuario AND u.senha=:senha", Usuario.class);
		query.setParameter("usuario", login.getUsuario());
		query.setParameter("senha", login.getSenha());
		
		Usuario usuario;
		
		try {
			usuario = query.getSingleResult();
			
		} catch(Exception e){
			throw new UsuarioNaoEncontradoException(e);
		} finally {			
			manager.close();
		}
		
		return usuario;
	}
	
	public List<Usuario> ranking(){
		EntityManager manager = factory.createEntityManager();
		
		TypedQuery<Usuario> query = manager.createQuery("SELECT DISTINCT u FROM Usuario u LEFT JOIN FETCH u.livrosLidos order by u.pontuacao desc", Usuario.class);
		List<Usuario> resultList = query.getResultList();
		
		manager.close();
		
		return resultList;
	}

	public Usuario buscarUsuarioPorId(Long id) throws UsuarioNaoEncontradoException {
		EntityManager manager = factory.createEntityManager();
		
		TypedQuery<Usuario> query = manager.createQuery("FROM Usuario u LEFT JOIN FETCH u.livrosLidos WHERE u.id=:id", Usuario.class);
		query.setParameter("id", id);
		
		Usuario usuario;
		
		try {
			usuario = query.getSingleResult();
			
		} catch(Exception e){
			throw new UsuarioNaoEncontradoException(e);
		} finally {			
			manager.close();
		}
		
		return usuario;
	}

	public void marcarLivroComoLido(Usuario usuario, Livro livro) throws UsuarioNaoEncontradoException {
		usuario = buscarUsuarioPorId(usuario.getId());
		usuario.marcarLivroComoLido(livro);
		
		atualizarUsuario(usuario);
	}
	
	private void atualizarUsuario(Usuario usuario){
		EntityManager manager = factory.createEntityManager();
		manager.getTransaction().begin();
		
		manager.merge(usuario);
		
		manager.getTransaction().commit();
		manager.close();
	}
	
}
