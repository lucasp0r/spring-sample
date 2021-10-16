package com.rachadel.error.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Manoel Rachadel Neto
 * @since  14 de out. de 2021
 */

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFountException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ResourceNotFountException(String message) {
		super(message);
	}
}
