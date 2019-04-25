package br.com.esseeujali.models;

import static org.junit.Assert.*;

import org.junit.Test;

public class UsuarioTest {

	@Test
	public void marcarLivroComLidoCorretamente() {
		Usuario usuario = new Usuario("usuario01", "usuario01", 0);
		Livro livro = new Livro("Livro01", "Autor01", 236, Categoria.FANTASIA, "capa");
		
		usuario.marcarLivroComoLido(livro);
		
		assertEquals(1, usuario.getQuantidadeLivrosLidos());
		assertTrue(usuario.isLivroLido(livro));
	}
	
	@Test
	public void naoMarcaUmLivroComoLidoMaisDeUmaVez(){
		Usuario usuario = new Usuario("usuario01", "usuario01", 0);
		Livro livro = new Livro("Livro01", "Autor01", 236, Categoria.FANTASIA, "capa");
		
		usuario.marcarLivroComoLido(livro);
		usuario.marcarLivroComoLido(livro);
		
		assertEquals(1, usuario.getQuantidadeLivrosLidos());
	}
	
	@Test
	public void adicionaPontosAoLerUmLivro(){
		Usuario usuario = new Usuario("usuario01", "usuario01", 0);
		Livro livro = new Livro("Livro01", "Autor01", 236, Categoria.FANTASIA, "capa");
		
		usuario.marcarLivroComoLido(livro);
		
		assertEquals(3, usuario.getPontuacao());
	}
	
	@Test
	public void naoAdicionaPontosAoTentarLerUmLivroRepetido(){
		Usuario usuario = new Usuario("usuario01", "usuario01", 0);
		Livro livro = new Livro(1L, "Livro01", "Autor01", 236, Categoria.FANTASIA, "capa");
		
		usuario.marcarLivroComoLido(livro);
		usuario.marcarLivroComoLido(livro);
		
		assertEquals(3, usuario.getPontuacao());
	}
	
	@Test
	public void ganhaUmTrofeuAoLer5LivrosDeUmaCategoria(){
		Usuario usuario = new Usuario("usuario01", "usuario01", 0);
		Livro livro01 = new Livro(1L, "Livro01", "Autor01", 236, Categoria.FANTASIA, "capa");
		Livro livro02 = new Livro(2L, "Livro02", "Autor02", 236, Categoria.FANTASIA, "capa");
		Livro livro03 = new Livro(3L, "Livro03", "Autor03", 236, Categoria.FANTASIA, "capa");
		Livro livro04 = new Livro(4L, "Livro04", "Autor04", 236, Categoria.FANTASIA, "capa");
		Livro livro05 = new Livro(5L, "Livro05", "Autor05", 236, Categoria.FANTASIA, "capa");
		
		usuario.marcarLivroComoLido(livro01);
		usuario.marcarLivroComoLido(livro02);
		usuario.marcarLivroComoLido(livro03);
		usuario.marcarLivroComoLido(livro04);
		usuario.marcarLivroComoLido(livro05);
		
		assertEquals(5, usuario.getQuantidadeLivrosLidos());
		assertTrue(usuario.getTrofeus().contains(Categoria.FANTASIA));
	}
	
	@Test
	public void naoGanhaUmTrofeuSeNaoTiverLido5LivrosDeUmaCategoria(){
		Usuario usuario = new Usuario("usuario01", "usuario01", 0);
		Livro livro01 = new Livro(1L, "Livro01", "Autor01", 236, Categoria.FANTASIA, "capa");
		Livro livro02 = new Livro(2L, "Livro02", "Autor02", 236, Categoria.FANTASIA, "capa");
		Livro livro03 = new Livro(3L, "Livro03", "Autor03", 236, Categoria.FANTASIA, "capa");
		Livro livro04 = new Livro(4L, "Livro04", "Autor04", 236, Categoria.FANTASIA, "capa");
		
		usuario.marcarLivroComoLido(livro01);
		usuario.marcarLivroComoLido(livro02);
		usuario.marcarLivroComoLido(livro03);
		usuario.marcarLivroComoLido(livro04);
		
		assertEquals(4, usuario.getQuantidadeLivrosLidos());
		assertFalse(usuario.getTrofeus().contains(Categoria.FANTASIA));
	}

}
