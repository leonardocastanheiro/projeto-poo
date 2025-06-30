package br.ucs.projetosistemaprodutos.exceptions;

public class InsufficientStockException extends Exception{
	private static final long serialVersionUID = 1L;
	public static final String MESSAGE = "Estoque insuficiente.";
	
	public InsufficientStockException() {
		super(MESSAGE);
	}
	
	public InsufficientStockException(String message) {
		super(MESSAGE + " " + message);
	}
	
	public InsufficientStockException(Throwable cause) {
		super(MESSAGE, cause);
	}
	
	public InsufficientStockException(String message, Throwable cause) {
		super(MESSAGE + " " + message, cause);
	}
	
	public InsufficientStockException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(MESSAGE + " " + message, cause, enableSuppression, writableStackTrace);
	}
}
