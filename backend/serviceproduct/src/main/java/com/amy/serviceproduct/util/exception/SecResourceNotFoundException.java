package com.amy.serviceproduct.util.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class SecResourceNotFoundException extends Exception{
	private static final long serialVersionUID = 1L;
	
	public SecResourceNotFoundException(String message){
		super(message);
	}
}
