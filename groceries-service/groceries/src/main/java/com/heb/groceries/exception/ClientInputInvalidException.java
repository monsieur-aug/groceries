package com.heb.groceries.exception;

/**
 * Represents an error event caused by improper input provided by the client.
 * 
 * @see com.heb.groceries.exception.ClientInputInvalidExceptionMapper
 */
public class ClientInputInvalidException extends RuntimeException {

	private static final long serialVersionUID = -2210993481735183459L;

	public ClientInputInvalidException(final String message) {
		super(message);
	}
}
