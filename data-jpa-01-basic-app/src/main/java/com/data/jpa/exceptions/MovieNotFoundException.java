package com.data.jpa.exceptions;

public class MovieNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public MovieNotFoundException() {

	}

	public MovieNotFoundException(String message) {
		super(message);
	}
}
