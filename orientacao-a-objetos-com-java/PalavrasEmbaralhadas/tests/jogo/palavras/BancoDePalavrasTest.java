package jogo.palavras;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import jogo.erros.LeituraArquivoException;

public class BancoDePalavrasTest {
	
	private String arquivoValido;
	private String arquivoInexistente;
	private String arquivoVazio;

	@Before
	public void before(){
		arquivoValido = "arquivoValido.txt";
		arquivoInexistente = "naoExiste.txt";
		arquivoVazio = "arquivoVazio.txt";
	}
	
	@Test
	public void retornaUmaPalavraFonte() throws LeituraArquivoException {
		BancoDePalavras bancoDePalavras = new BancoDePalavras();
		
		assertTrue(bancoDePalavras.getPalavraAleatoria() instanceof String);
	}

	@Test
	public void retornaUmaPalavra() throws LeituraArquivoException {
		BancoDePalavras bancoDePalavras = new BancoDePalavras(arquivoValido);
		
		assertTrue(bancoDePalavras.getPalavraAleatoria() instanceof String);
	}
	
	@Test(expected=LeituraArquivoException.class)
	public void arquivoPassadoNaoExiste() throws LeituraArquivoException {
		BancoDePalavras bancoDePalavras = new BancoDePalavras(arquivoInexistente);
		
		bancoDePalavras.getPalavraAleatoria();
	}
	
	@Test(expected=LeituraArquivoException.class)
	public void arquivoPassadoEstaVazio() throws LeituraArquivoException {
		BancoDePalavras bancoDePalavras = new BancoDePalavras(arquivoVazio);
		
		bancoDePalavras.getPalavraAleatoria();
	}

}
