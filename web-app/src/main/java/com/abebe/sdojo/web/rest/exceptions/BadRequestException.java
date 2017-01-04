package com.abebe.sdojo.web.rest.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException {
	private static final long serialVersionUID = -6043078946038922555L;

	public BadRequestException() {
    }

    public BadRequestException(Throwable cause) {
        super(cause);
    }
}
