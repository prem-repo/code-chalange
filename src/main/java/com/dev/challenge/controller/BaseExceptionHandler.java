package com.dev.challenge.controller;

import com.dev.challenge.exception.ErrorInfo;
import com.dev.challenge.exception.HttpException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;



@RestController
@ControllerAdvice
public class BaseExceptionHandler extends ResponseEntityExceptionHandler {
	

	@ExceptionHandler(HttpException.class)
	public ResponseEntity<ErrorInfo> exHttpException(HttpException ex) {
		
		ErrorInfo errorInfo = new ErrorInfo(ex.getCode(), ex.getMessage());
		
		return new ResponseEntity<>(errorInfo, ex.getHttpStatusCode());
	}

}
