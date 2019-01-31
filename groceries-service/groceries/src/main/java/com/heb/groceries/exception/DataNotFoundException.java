package com.heb.groceries.exception;

/**
 * Represents an error event caused by a failure to find an item specified in
 * the request.
 * 
 * @see com.heb.groceries.exception.DataNotFoundExceptionMapper
 */
public class DataNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -6225493821788006421L;

	public DataNotFoundException(final String message) {
		super(message);
	}
}
