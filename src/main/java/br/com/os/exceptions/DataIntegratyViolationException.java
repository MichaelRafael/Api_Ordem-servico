package br.com.os.exceptions;

public class DataIntegratyViolationException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public DataIntegratyViolationException(String message) {
		super(message);
	}

}
