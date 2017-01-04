package com.abebe.sdojo.core.services.exception;

public class AccountExistsException extends RuntimeException {
	private static final long serialVersionUID = -3063714444991034829L;

	public AccountExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public AccountExistsException(String message) {
        super(message);
    }

    public AccountExistsException() {
        super();
    }
}
