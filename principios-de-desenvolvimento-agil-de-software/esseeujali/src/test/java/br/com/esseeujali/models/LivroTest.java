package br.com.esseeujali.models;

import static org.junit.Assert.*;

import org.junit.Test;

public class LivroTest {

	@Test
	public void devolveUmPontoSeoLivroTiverMenosDe100Paginas() {
		Livro livro = new Livro("Livro01", "Autor01", 99, Categoria.FANTASIA, "capa");
		
		assertEquals(1, livro.getPontos());
	}
	
	@Test
	public void devolveDoisPontoSeoLivroTiverDe100PaginasAteMenosDe200Paginas() {
		Livro livro01 = new Livro("Livro01", "Autor01", 100, Categoria.FANTASIA, "capa");
		Livro livro02 = new Livro("Livro01", "Autor01", 199, Categoria.FANTASIA, "capa");
		
		assertEquals(2, livro01.getPontos());
		assertEquals(2, livro02.getPontos());
	}
	
	@Test
	public void devolveTresPontoSeoLivroTiverDe200PaginasAteMenosDe00Paginas() {
		Livro livro01 = new Livro("Livro01", "Autor01", 200, Categoria.FANTASIA, "capa");
		Livro livro02 = new Livro("Livro01", "Autor01", 299, Categoria.FANTASIA, "capa");
		
		assertEquals(3, livro01.getPontos());
		assertEquals(3, livro02.getPontos());
	}
	
	@Test
	public void devolveSeisPontoSeoLivroTiverDe500PaginasAteMenosDe600Paginas() {
		Livro livro01 = new Livro("Livro01", "Autor01", 500, Categoria.FANTASIA, "capa");
		Livro livro02 = new Livro("Livro01", "Autor01", 599, Categoria.FANTASIA, "capa");
		
		assertEquals(6, livro01.getPontos());
		assertEquals(6, livro02.getPontos());
	}

}
