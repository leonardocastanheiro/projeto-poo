package br.ucs.projetosistemaprodutos.exceptions;

public class UserNotFoundException extends Exception{
	private static final long serialVersionUID = 1L;
	public static final String MESSAGE = "Usuário/Senha incorretos";
	
	public UserNotFoundException() {
		super(MESSAGE);
	}
	
	public UserNotFoundException(String message) {
		super(MESSAGE + " " + message);
	}
	
	public UserNotFoundException(Throwable cause) {
		super(MESSAGE, cause);
	}
	
	public UserNotFoundException(String message, Throwable cause) {
		super(MESSAGE + " " + message, cause);
	}
	
	public UserNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(MESSAGE + " " + message, cause, enableSuppression, writableStackTrace);
	}
}
