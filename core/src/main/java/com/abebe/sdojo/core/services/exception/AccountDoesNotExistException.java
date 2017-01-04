package com.abebe.sdojo.core.services.exception;


public class AccountDoesNotExistException extends RuntimeException {
	private static final long serialVersionUID = -942807451241379136L;

	public AccountDoesNotExistException(Throwable cause) {
        super(cause);
    }

    public AccountDoesNotExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public AccountDoesNotExistException(String message) {
        super(message);
    }

    public AccountDoesNotExistException() {
    }
}
