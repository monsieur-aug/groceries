package com.heb.groceries.dao.exception;

/**
 * Represents a general data access object (DAO) error.
 */
public class DAOException extends RuntimeException {

	private static final long serialVersionUID = 903004154398214263L;

	public DAOException(final String message) {
		super(message);
	}

	public DAOException(final Throwable throwable) {
		super(throwable);
	}

}
