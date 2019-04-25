package br.com.esseeujali.exceptions;

public class LivroNaoEncontradoException extends Exception {

	private static final long serialVersionUID = -4290409691067046568L;
	
	public LivroNaoEncontradoException(String msg) {
		super(msg);
	}

}
