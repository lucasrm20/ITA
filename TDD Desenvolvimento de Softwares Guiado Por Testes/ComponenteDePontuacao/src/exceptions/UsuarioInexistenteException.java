package exceptions;

public class UsuarioInexistenteException extends Exception {
	
	private static final long serialVersionUID = 2644910945324665831L;

	public UsuarioInexistenteException(String message) {
		super(message);
	}
}
