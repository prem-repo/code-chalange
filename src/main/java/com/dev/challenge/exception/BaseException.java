package com.dev.challenge.exception;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class BaseException extends RuntimeException {

	private static final long serialVersionUID = -1362660970202828096L;
	private Integer code;
	private String message;

	public BaseException() {
		super();
	}

	public BaseException(final String message) {
		super(message);
		this.message = message;
	}

	public BaseException(final String message, final Integer code) {
		super(message);
		this.message=message;
		this.code = code;
		

	}

}
