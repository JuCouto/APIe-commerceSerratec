package com.example.ecommerce.exceptions;

public class NoSuchElementFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NoSuchElementFoundException(String message) {
		super(message);
	}
}
