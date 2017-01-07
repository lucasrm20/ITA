package br.com.esseeujali.util.token.exceptions;

public class TokenExpiradoException extends Exception {

	private static final long serialVersionUID = -8108487479807494705L;

	public TokenExpiradoException(String msg) {
		super(msg);
	}
}
