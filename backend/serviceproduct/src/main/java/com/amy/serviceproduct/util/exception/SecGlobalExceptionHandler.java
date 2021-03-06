package com.amy.serviceproduct.util.exception;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class SecGlobalExceptionHandler extends ResponseEntityExceptionHandler {
	@ExceptionHandler(SecResourceNotFoundException.class)
	public ResponseEntity<?> resourceNotFoundException(SecResourceNotFoundException ex, WebRequest request) {
		SecErrorDetails errorDetails = new SecErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
		//ErrorDetails errorDetails = new ErrorDetails(new Date(), " -----xxxxx---- ", request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> globleExcpetionHandler(Exception ex, WebRequest request) {
		SecErrorDetails errorDetails = new SecErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}
 
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
		HttpHeaders headers, HttpStatus status, WebRequest request) {
            SecErrorDetails errorDetails = new SecErrorDetails(new Date(), "Validation Failed", ex.getBindingResult().toString());
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	} 
}
