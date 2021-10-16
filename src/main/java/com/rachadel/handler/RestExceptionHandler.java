package com.rachadel.handler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.rachadel.error.ResponseError;
import com.rachadel.error.ResponseErrorDetail;
import com.rachadel.error.exception.ResourceNotFountException;

/**
 * @author Manoel Rachadel Neto
 * @since 14 de out. de 2021
 */
@ControllerAdvice
public class RestExceptionHandler {
	
	@ExceptionHandler(ResourceNotFountException.class)
	public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFountException rnfException) {
		ResponseErrorDetail responseErrorDetail = ResponseErrorDetail.builder()
				.title("resource not found")
				.detail(rnfException.getMessage())
				.timestamp(new Date().getTime())
				.developerMessage(rnfException.getClass().getName())
				.build();

		ResponseError responseError = ResponseError.builder()
				.status(HttpStatus.NOT_FOUND.value())
				.errors(Collections.singletonList(responseErrorDetail))
				.build();

		return new ResponseEntity<>(responseError, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException manvException) {
		List<ResponseErrorDetail> lstErrors = new ArrayList<>();
		Long now = new Date().getTime(); 
		for (FieldError fieldError : manvException.getBindingResult().getFieldErrors()) {
			lstErrors.add(ResponseErrorDetail.builder()
					.title("validation error in field '" + fieldError.getField() + "'")
					.detail(fieldError.getDefaultMessage())
					.timestamp(now)
					.developerMessage(manvException.getClass().getName())
					.build());
		}

		ResponseError responseError = ResponseError.builder()
				.status(HttpStatus.BAD_REQUEST.value())
				.errors(lstErrors).build();
		return new ResponseEntity<>(responseError, HttpStatus.BAD_REQUEST);
	}
}
