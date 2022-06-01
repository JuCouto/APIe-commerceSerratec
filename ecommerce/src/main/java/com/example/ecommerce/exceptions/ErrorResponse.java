package com.example.ecommerce.exceptions;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {
	private final Integer status;
	private final String message;
	private List<String> details;
	
	public ErrorResponse(Integer status, String message) {
		super();
		this.status = status;
		this.message = message;
	}

	public ErrorResponse(Integer status, String message, List<String> details) {
		super();
		this.status = status;
		this.message = message;
		this.details = details;
	}

	public List<String> getDetails() {
		return details;
	}

	public void setDetails(List<String> details) {
		this.details = details;
	}

	public Integer getStatus() {
		return status;
	}

	public String getMessage() {
		return message;
	}
}
