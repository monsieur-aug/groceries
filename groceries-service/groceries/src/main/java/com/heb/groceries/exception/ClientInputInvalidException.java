package com.heb.groceries.exception;

public class ClientInputInvalidException extends RuntimeException {

	private static final long serialVersionUID = -2210993481735183459L;

	public ClientInputInvalidException(final String message) {
		super(message);
	}
}
