package com.heb.groceries.exception;

public class UnsupportedQueryException extends RuntimeException {

	private static final long serialVersionUID = 4330171006104482640L;

	public UnsupportedQueryException(final String message) {
		super(message);
	}
}
