package com.example.ecommerce.exceptions;

public class EmptyListException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EmptyListException (String message) {
		super(message);
	}
}
