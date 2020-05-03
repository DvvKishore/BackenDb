package com.example.backenddb.exception;

import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class OurExceptionHandler {
	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity handleNoSerchElementException()
	{
		ResponseEntity re =new ResponseEntity(HttpStatus.NOT_FOUND);
		return re;
		
	}

}
