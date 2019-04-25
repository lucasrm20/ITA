package br.com.esseeujali.util.token;

import java.util.HashMap;
import java.util.Map;

import br.com.esseeujali.util.token.exceptions.TokenExpiradoException;
import br.com.esseeujali.util.token.exceptions.TokenInvalidoException;

public class TokenManager {

private static Map<String, Token> tokensAtivos = new HashMap<String, Token>();
	
	public static void guardarToken(Token token){
		tokensAtivos.put(token.getToken(), token);
	}
	
	public static boolean isTokenAtivo(String token){
		return tokensAtivos.containsKey(token);
	}
	
	public static Token getToken(String token) throws TokenInvalidoException, TokenExpiradoException{
		if(!isTokenAtivo(token))
			throw new TokenInvalidoException("Token Inválido!");
		
		Token tk = tokensAtivos.get(token);
		
		if(tk.isTokenExpirado()){
			removeTokenExpirado(tk);
			throw new TokenExpiradoException("Token Expirado!");
		}
		
		return tk;
	}
	
	private static void removeTokenExpirado(Token token){
		tokensAtivos.remove(token.getToken());
	}
	
	public static int getQuantidadeDeTokensAtivos(){
		return tokensAtivos.size();
	}
	
	public static void invalidarTodosOsTokensAtivos(){
		tokensAtivos = new HashMap<String, Token>();
	}
	
}
