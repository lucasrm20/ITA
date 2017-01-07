package br.com.esseeujali.util.token;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;

import br.com.esseeujali.models.Usuario;

public class TokenTest {

	@Test
	public void geraUmTokenAleatorioAoConstruirInformandoUmUsuario() {
		Usuario usuario = new Usuario("usuario", "senha", 10);
		Token token = new Token(usuario, new Relogio());
		
		assertNotNull(token);
		assertNotNull(token.getToken());
		assertFalse(token.getToken().isEmpty());
	}
	
	@Test
	public void defineADataDeVencimentoAoConstruirInformandoUmUsuario(){
		Usuario usuario = new Usuario("usuario", "senha", 10);
		Token token = new Token(usuario, new Relogio());
		
		assertNotNull(token);
		assertNotNull(token.getDataVencimento());
	}
	
	@Test
	public void defineADataDeVencimentoPara7DiasNoFuturo(){
		Usuario usuario = new Usuario("usuario", "senha", 10);
		Token token = new Token(usuario, new Relogio());
		
		LocalDate dataExperada = LocalDate.now().plusDays(7);
		
		assertEquals(dataExperada, token.getDataVencimento());
	}
	
	@Test
	public void retornaFalseSeOTokenNaoEstiverExpirado(){
		Usuario usuario = new Usuario("usuario", "senha", 10);
		Token token = new Token(usuario, new Relogio());
		
		assertFalse(token.isTokenExpirado());
	}
	
	@Test
	public void retornaTrueSeOTokenNaoEstiverExpirado(){
		Usuario usuario = new Usuario("usuario", "senha", 10);
		Relogio umAnoAtras = new Relogio(LocalDate.now().minusYears(1));
		
		Token token = new Token(usuario, umAnoAtras);
		
		assertTrue(token.isTokenExpirado());
	}

}
