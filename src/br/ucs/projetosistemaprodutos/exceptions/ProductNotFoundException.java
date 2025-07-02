package br.ucs.projetosistemaprodutos.exceptions;

public class ProductNotFoundException extends Exception{
	private static final long serialVersionUID = 1L;
	public static final String MESSAGE = "Produto não encontrado.";
	
	public ProductNotFoundException() {
		super(MESSAGE);
	}
	
	public ProductNotFoundException(String message) {
		super(MESSAGE + " " + message);
	}
	
	public ProductNotFoundException(Throwable cause) {
		super(MESSAGE, cause);
	}
	
	public ProductNotFoundException(String message, Throwable cause) {
		super(MESSAGE + " " + message, cause);
	}
	
	public ProductNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(MESSAGE + " " + message, cause, enableSuppression, writableStackTrace);
	}
}
