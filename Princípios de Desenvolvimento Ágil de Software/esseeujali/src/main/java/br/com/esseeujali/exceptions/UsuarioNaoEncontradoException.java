package br.com.esseeujali.exceptions;

public class UsuarioNaoEncontradoException extends Exception {

	private static final long serialVersionUID = 5262224885036988066L;

	public UsuarioNaoEncontradoException(Exception e) {
		super(e);
	}
	
}
