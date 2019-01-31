package com.heb.groceries.dao.exception;

/**
 * Represents an unrecoverable error with the data access object (DAO)
 * configuration.
 */
public class DAOConfigurationException extends RuntimeException {

	private static final long serialVersionUID = 1222348534190870382L;

	public DAOConfigurationException(final String message) {
		super(message);
	}

	public DAOConfigurationException(final Throwable throwable) {
		super(throwable);
	}

	public DAOConfigurationException(final String message, final Throwable throwable) {
		super(message, throwable);
	}

}
