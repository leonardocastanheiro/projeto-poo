package br.ucs.projetosistemaprodutos.exceptions;

public class EmptyDataException extends Exception{
	private static final long serialVersionUID = 1L;
	public static final String MESSAGE = "Esse campo deve ser preenchido";
	
	public EmptyDataException() {
		super(MESSAGE);
	}
	
	public EmptyDataException(String message) {
		super(MESSAGE + " " + message);
	}
	
	public EmptyDataException(Throwable cause) {
		super(MESSAGE, cause);
	}
	
	public EmptyDataException(String message, Throwable cause) {
		super(MESSAGE + " " + message, cause);
	}
	
	public EmptyDataException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(MESSAGE + " " + message, cause, enableSuppression, writableStackTrace);
	}
}
