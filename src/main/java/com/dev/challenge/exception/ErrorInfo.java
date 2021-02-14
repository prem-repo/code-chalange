package com.dev.challenge.exception;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Setter
@Getter
@ToString
public class ErrorInfo implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = -7858479108670282133L;

	private Integer code = null;

	private String message = null;

	private String fields = null;

	public ErrorInfo() {
		super();
	}

	public ErrorInfo(final int code, final String message) {
		this.code = code;
		this.message = message;
	}

	public ErrorInfo(final String code, final String message, final String moreInfoUrl, final String moreErrorInfo) {

	}



}
