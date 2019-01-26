package com.heb.groceries.model;

public class ErrorResponse {

	private int		errorCode;
	private String	errorMessage;

	public ErrorResponse() {

	}

	public ErrorResponse(final int errorCode, final String errorMessage) {
		setErrorCode(errorCode);
		setErrorMessage(errorMessage);
	}

	public int getErrorCode() {
		return this.errorCode;
	}

	public void setErrorCode(final int errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return this.errorMessage;
	}

	public void setErrorMessage(final String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
