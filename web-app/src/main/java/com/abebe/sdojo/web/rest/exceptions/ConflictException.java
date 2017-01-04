package com.abebe.sdojo.web.rest.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.CONFLICT)
public class ConflictException extends RuntimeException {
	private static final long serialVersionUID = -1791761470925666831L;

	public ConflictException() {
    }

    public ConflictException(Throwable cause) {
        super(cause);
    }
}
