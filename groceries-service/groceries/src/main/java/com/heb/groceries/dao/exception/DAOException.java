package com.heb.groceries.dao.exception;

public class DAOException extends RuntimeException {

	private static final long serialVersionUID = 903004154398214263L;

	public DAOException(final String message) {
		super(message);
	}

	public DAOException(final Throwable throwable) {
		super(throwable);
	}

}
