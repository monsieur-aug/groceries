package com.heb.groceries.exception;

/**
 * Represents an error event caused by improperly-specified query parameters from
 * the client.
 * 
 * @see com.heb.groceries.exception.UnsupportedQueryExceptionMapper
 */
public class UnsupportedQueryException extends RuntimeException {

	private static final long serialVersionUID = 4330171006104482640L;

	public UnsupportedQueryException(final String message) {
		super(message);
	}
}
