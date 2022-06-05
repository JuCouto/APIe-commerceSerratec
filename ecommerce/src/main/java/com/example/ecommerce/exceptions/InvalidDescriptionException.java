package com.example.ecommerce.exceptions;

public class InvalidDescriptionException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidDescriptionException (String message) {
		super(message);
	}
}
