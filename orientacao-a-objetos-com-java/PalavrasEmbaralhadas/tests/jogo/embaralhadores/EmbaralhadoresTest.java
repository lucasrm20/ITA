package jogo.embaralhadores;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

import jogo.embaralhadores.Embaralhador;
import jogo.embaralhadores.EmbaralhadorAleatorio;
import jogo.embaralhadores.EmbaralhadorInvertido;

public class EmbaralhadoresTest {

	Embaralhador embaralhador;
	
	@Test
	public void retornaAPalavraEmbaralhadaInvertida() {
		embaralhador = new EmbaralhadorInvertido();
		
		assertNotEquals("tomate", embaralhador.embaralharPalavra("tomate"));
		assertEquals("etamot", embaralhador.embaralharPalavra("tomate"));
	}
	
	@Test
	public void retornaAPalavraEmbaralhadaAleatoria() {
		embaralhador = new EmbaralhadorAleatorio();
		assertNotEquals("tomate", embaralhador.embaralharPalavra("tomate"));
	}

}
