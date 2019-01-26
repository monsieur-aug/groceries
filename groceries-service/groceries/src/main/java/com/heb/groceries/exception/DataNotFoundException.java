package com.heb.groceries.exception;

public class DataNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -6225493821788006421L;

	public DataNotFoundException(final String message) {
		super(message);
	}
}
