package com.joaojbarros.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)
public class UnexpectedException extends Exception {
	private static final long serialVersionUID = 5295140893041880586L;

	public UnexpectedException() {
        super("Unexpected error");
    }
}