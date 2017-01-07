package br.com.esseeujali.dao;

import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.esseeujali.exceptions.LivroNaoEncontradoException;
import br.com.esseeujali.exceptions.UsuarioNaoEncontradoException;
import br.com.esseeujali.models.Categoria;
import br.com.esseeujali.models.Livro;
import br.com.esseeujali.models.Usuario;
import br.com.esseeujali.models.rest.Login;

/**
 * OBS.: Cada teste é um cenário isolado que segue o seguinte molde:
 * 
 * 		1 - As tabelas são geradas;
 * 		2 - O cenário do teste é executado;
 * 		3 - As tabelas são dropadas.
 * 
 * 	Visto isso, o processo de execução desta classe de testes pode vir a ser bem lento.
 * 
 * @author lucas
 *
 */
public class UsuarioDAOTest {
	
	private EntityManagerFactory factory;

	@Before
	public void before(){
		factory = Persistence.createEntityManagerFactory("test");
	}
	
	@After
	public void after(){
		factory.close();
	}

	@Test
	public void salvaUmNovoUsuario() {
		Usuario usuario = new Usuario("usuario01", "usuario01", 0);
		
		UsuarioDAO dao = new UsuarioDAO(factory);
		Usuario usuarioSalvo = dao.salvar(usuario);
		
		assertNotNull(usuarioSalvo);
		assertEquals(usuario.getUsuario(), usuarioSalvo.getUsuario());
	}
	
	@Test
	public void buscaUsuarioParaLogin() throws UsuarioNaoEncontradoException{
		Usuario usuario = new Usuario("usuario01", "usuario01", 0);
		
		UsuarioDAO dao = new UsuarioDAO(factory);
		dao.salvar(usuario);
		
		Login login = new Login(usuario.getUsuario(), usuario.getSenha());
		Usuario usuarioBuscado = dao.buscaLogin(login);
		
		assertNotNull(usuarioBuscado);
		assertEquals(login.getUsuario(), usuarioBuscado.getUsuario());
	}
	
	@Test(expected=UsuarioNaoEncontradoException.class)
	public void lancaExcecaoAoBuscarUsuarioInexistenteParaLogin() throws UsuarioNaoEncontradoException{
		Login login = new Login("naoExiste", "naoExiste");
		
		UsuarioDAO dao = new UsuarioDAO(factory);
		dao.buscaLogin(login);
	}
	
	@Test
	public void buscaUsuarioPorId() throws UsuarioNaoEncontradoException{
		Usuario usuario = new Usuario("usuario01", "usuario01", 0);
		
		UsuarioDAO dao = new UsuarioDAO(factory);
		dao.salvar(usuario);
		
		Usuario usuarioBuscado = dao.buscarUsuarioPorId(1L);
		
		assertNotNull(usuarioBuscado);
		assertEquals(new Long(1), usuarioBuscado.getId());
		assertEquals(usuario.getUsuario(), usuarioBuscado.getUsuario());
	}
	
	@Test(expected=UsuarioNaoEncontradoException.class)
	public void lancaExcecaoAoBuscarUmIdInexistente() throws UsuarioNaoEncontradoException{
		UsuarioDAO dao = new UsuarioDAO(factory);
		dao.buscarUsuarioPorId(1L);
	}
	
	@Test
	public void retornaORankingDeUsuarios(){
		Usuario usuario01 = new Usuario("usuario01", "usuario01", 10);
		Usuario usuario02 = new Usuario("usuario02", "usuario02", 20);
		Usuario usuario03 = new Usuario("usuario03", "usuario03", 30);
		
		UsuarioDAO dao = new UsuarioDAO(factory);
		dao.salvar(usuario01);
		dao.salvar(usuario02);
		dao.salvar(usuario03);
		
		List<Usuario> ranking = dao.ranking();
		
		assertNotNull(ranking);
		assertFalse(ranking.isEmpty());
		assertEquals(3, ranking.size());
		assertEquals(usuario03.getUsuario(), ranking.get(0).getUsuario());
		assertEquals(usuario02.getUsuario(), ranking.get(1).getUsuario());
		assertEquals(usuario01.getUsuario(), ranking.get(2).getUsuario());
	}
	
	@Test
	public void adicionaUmLivroLidoAoUsuario() throws UsuarioNaoEncontradoException, LivroNaoEncontradoException{
		Usuario usuario = new Usuario("usuario01", "usuario01", 0);
		Livro livro = new Livro("livro01", "autor01", 150, Categoria.FANTASIA, "capa");
		
		UsuarioDAO usuarioDAO = new UsuarioDAO(factory);
		usuarioDAO.salvar(usuario);
		
		LivroDAO livroDAO = new LivroDAO(factory);
		livroDAO.salvar(livro);
		
		Usuario usuarioBuscado = usuarioDAO.buscarUsuarioPorId(1L);
		Livro livroBuscado = livroDAO.buscaLivro(1L);
		
		usuarioDAO.marcarLivroComoLido(usuarioBuscado, livroBuscado);
		
		Usuario usuarioAtualizado = usuarioDAO.buscarUsuarioPorId(1L);
		
		assertEquals(1, usuarioAtualizado.getQuantidadeLivrosLidos());
		assertTrue(usuarioAtualizado.getLivrosLidos().contains(livroBuscado));
	}

}
