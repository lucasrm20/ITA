package br.com.esseeujali.util.token;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.After;
import org.junit.Test;

import br.com.esseeujali.models.Usuario;
import br.com.esseeujali.util.token.exceptions.TokenExpiradoException;
import br.com.esseeujali.util.token.exceptions.TokenInvalidoException;

public class TokenManagerTest {
	
	@After
	public void after(){
		TokenManager.invalidarTodosOsTokensAtivos();
	}

	@Test
	public void guardaUmNovoToken() {
		Usuario usuario = new Usuario("usuario", "senha", 10);
		Token token = new Token(usuario , new Relogio());
		
		TokenManager.guardarToken(token);
		
		assertEquals(1, TokenManager.getQuantidadeDeTokensAtivos());
	}
	
	@Test
	public void retornaTrueSeUmTokenEstiverAtivo(){
		Usuario usuario = new Usuario("usuario", "senha", 10);
		Token token = new Token(usuario , new Relogio());
		
		TokenManager.guardarToken(token);
		
		assertTrue(TokenManager.isTokenAtivo(token.getToken()));
	}
	
	@Test
	public void retornaFalseSeUmTokenNaoEstiverAtivo(){
		Usuario usuario = new Usuario("usuario", "senha", 10);
		Token token = new Token(usuario , new Relogio());
		
		assertFalse(TokenManager.isTokenAtivo(token.getToken()));
	}
	
	@Test
	public void recuperaUmTokenAtivo() throws TokenInvalidoException, TokenExpiradoException{
		Usuario usuario = new Usuario("usuario", "senha", 10);
		Token token = new Token(usuario , new Relogio());
		
		TokenManager.guardarToken(token);
		Token tokenRecuperado = TokenManager.getToken(token.getToken());
		
		assertNotNull(tokenRecuperado);
		assertEquals(token.getToken(), tokenRecuperado.getToken());
		assertEquals(token.getDataVencimento(), tokenRecuperado.getDataVencimento());
	}
	
	@Test(expected=TokenInvalidoException.class)
	public void lancaExcecaoSeAoTentarRecuperarUmTokenInativo() throws TokenInvalidoException, TokenExpiradoException{
		Usuario usuario = new Usuario("usuario", "senha", 10);
		Token token = new Token(usuario , new Relogio());
		
		TokenManager.getToken(token.getToken());
	}
	
	@Test(expected=TokenExpiradoException.class)
	public void lancaExcecaoAoTentarRecuperarUmTokenExpirado() throws TokenInvalidoException, TokenExpiradoException{
		Usuario usuario = new Usuario("usuario", "senha", 10);
		Relogio umAnoAtras = new Relogio(LocalDate.now().minusYears(1));
		
		Token token = new Token(usuario , umAnoAtras );
		
		TokenManager.guardarToken(token);
		TokenManager.getToken(token.getToken());
	}
	
	@Test
	public void desativaUmTokenExpiradoAoTentarRecuperalo() throws TokenInvalidoException{
		Usuario usuario = new Usuario("usuario", "senha", 10);
		Relogio umAnoAtras = new Relogio(LocalDate.now().minusYears(1));
		
		Token token = new Token(usuario, umAnoAtras);
		TokenManager.guardarToken(token);
		
		try {
			TokenManager.getToken(token.getToken());
			fail();
		} catch (TokenExpiradoException e) {
			assertFalse(TokenManager.isTokenAtivo(token.getToken()));
		}
		
	}

}
