package com.dev.challenge.exception;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Setter
@Getter
@ToString
public class HttpException extends RuntimeException {
	private static final long serialVersionUID = -7740908683155023251L;
	private int code;
	private String message;
	private HttpStatus httpStatusCode;
	private String fields ;


	public HttpException() {
		super();
	}

	public HttpException(final String message) {
		super(message);
		this.message = message;
	}

	public HttpException(final String message, final int code, HttpStatus httpStatusCode) {
		super(message);
		this.message=message;
		this.code = code;
		this.httpStatusCode=httpStatusCode;
	}

	


	
}
