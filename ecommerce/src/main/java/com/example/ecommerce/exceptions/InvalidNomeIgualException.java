package com.example.ecommerce.exceptions;

public class InvalidNomeIgualException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InvalidNomeIgualException (String message) {
		super(message);
	}
}
