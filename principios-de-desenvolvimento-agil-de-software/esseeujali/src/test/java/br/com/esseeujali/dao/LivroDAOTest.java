package br.com.esseeujali.dao;

import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.esseeujali.exceptions.LivroNaoEncontradoException;
import br.com.esseeujali.models.Categoria;
import br.com.esseeujali.models.Livro;

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
public class LivroDAOTest {
	
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
	public void salvaUmNovoLivro(){
		Livro livro = new Livro("livro01", "autor01", 100, Categoria.FANTASIA, "capa");
		
		LivroDAO dao = new LivroDAO(factory);
		Livro livroSalvo = dao.salvar(livro);
		
		assertNotNull(livro.getId());
		assertEquals(livro.getTitulo(), livroSalvo.getTitulo());
	}

	@Test
	public void buscaTodosOsLivrosCadastrados() {
		Livro livro01 = new Livro("livro01", "autor01", 100, Categoria.FANTASIA, "capa");
		Livro livro02 = new Livro("livro02", "autor02", 100, Categoria.FANTASIA, "capa");
		Livro livro03 = new Livro("livro03", "autor03", 100, Categoria.FANTASIA, "capa");
		
		LivroDAO dao = new LivroDAO(factory);
		dao.salvar(livro01);
		dao.salvar(livro02);
		dao.salvar(livro03);
		
		List<Livro> livros = dao.buscaTodos();
		
		assertNotNull(livros);
		assertFalse(livros.isEmpty());
		assertEquals(3, livros.size());
		assertEquals(livro01.getTitulo(), livros.get(0).getTitulo());
		assertEquals(livro02.getTitulo(), livros.get(1).getTitulo());
		assertEquals(livro03.getTitulo(), livros.get(2).getTitulo());
	}
	
	@Test
	public void buscaLivroPorId() throws LivroNaoEncontradoException{
		Livro livro = new Livro("livro01", "autor01", 100, Categoria.FANTASIA, "capa");
		
		LivroDAO dao = new LivroDAO(factory);
		dao.salvar(livro);
		
		Livro livroBuscado = dao.buscaLivro(1L);
		
		assertNotNull(livroBuscado);
		assertEquals(new Long(1), livro.getId());
		assertEquals(livro.getTitulo(), livroBuscado.getTitulo());
	}
	
	@Test(expected=LivroNaoEncontradoException.class)
	public void retornaNullAobuscarLivroInexistentePorId() throws LivroNaoEncontradoException {
		LivroDAO dao = new LivroDAO(factory);
		dao.buscaLivro(1L);
	}

}
