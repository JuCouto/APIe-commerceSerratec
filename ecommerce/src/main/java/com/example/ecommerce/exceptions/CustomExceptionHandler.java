package com.example.ecommerce.exceptions;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request){
		List<String> details = new ArrayList<>();
		details.add(ex.getLocalizedMessage());
		HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		ErrorResponse error = new ErrorResponse(httpStatus.value(), "Erro Interno no Sevidor", details);
		
		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);	
	}
	
	@ExceptionHandler(NoSuchElementFoundException.class)
	public final ResponseEntity<Object> handleUserNotFoundException(NoSuchElementFoundException ex, WebRequest request){
		List<String> details = new ArrayList<>();
		details.add(ex.getLocalizedMessage());
		HttpStatus httpStatus = HttpStatus.NOT_FOUND;
		ErrorResponse error = new ErrorResponse(httpStatus.value(), "Registro não encontrado", details);
		
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);	
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request){
		List<String> details = new ArrayList<>();
		for (ObjectError error : ex.getBindingResult().getAllErrors()) {
			details.add(error.getDefaultMessage());
		}
		HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
		ErrorResponse error = new ErrorResponse(httpStatus.value(), "Falha na Validação dos Dados da Requisição", details);
		
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
	
	@Override
	protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request){
		List<String> details = new ArrayList<>();
		details.add(ex.getLocalizedMessage());
		details.add("Parâmetro não processado: " + ex.getParameterName());
		ErrorResponse error = new ErrorResponse(HttpStatus.BAD_REQUEST.value(),
				"Falha na Validação dos Dados da Requisição", details);
		
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	protected ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex){
		List<String> details = new ArrayList<>();
		details.add(ex.getLocalizedMessage());
		ErrorResponse error = new ErrorResponse(HttpStatus.BAD_REQUEST.value(),
				"Falha na Validação dos Dados da Requisição", details);
		
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(InvalidEmailException.class)
		public final ResponseEntity<Object> handleInvalidEmailException(InvalidEmailException ex, WebRequest request){
		List<String> details = new ArrayList<>();
		details.add(ex.getLocalizedMessage());
		HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
		ErrorResponse error = new ErrorResponse(httpStatus.value(), "Email inválido", details);
		
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(InvalidCpfException.class)
		public final ResponseEntity<Object> handleInvalidCpfException(InvalidCpfException ex, WebRequest request){
		List<String> details = new ArrayList<>();
		details.add(ex.getLocalizedMessage());
		HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
		ErrorResponse error = new ErrorResponse(httpStatus.value(), "Cpf inválido", details);
		
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(EmptyListException.class)
		public final ResponseEntity<Object> handleEmptyListException(EmptyListException ex, WebRequest request){
		List<String> details = new ArrayList<>();
		details.add(ex.getLocalizedMessage());
		HttpStatus httpStatus = HttpStatus.NO_CONTENT;
		ErrorResponse error = new ErrorResponse(httpStatus.value(), "Lista vazia", details);
		
		return new ResponseEntity<>(error, HttpStatus.NO_CONTENT);
	}
	
	@ExceptionHandler(InvalidDescriptionException.class)
		public final ResponseEntity<Object> handleInvalidDescriptionException(InvalidDescriptionException ex, WebRequest request){
		List<String> details = new ArrayList<>();
		details.add(ex.getLocalizedMessage());
		HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
		ErrorResponse error = new ErrorResponse(httpStatus.value(), "Descrição inválido", details);
		
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(InvalidNomeIgualException.class)
	public final ResponseEntity<Object> handleInvalidNomeIgualException(InvalidNomeIgualException ex, WebRequest request){
	List<String> details = new ArrayList<>();
	details.add(ex.getLocalizedMessage());
	HttpStatus httpStatus = HttpStatus.CONFLICT;
	ErrorResponse error = new ErrorResponse(httpStatus.value(), "Nome inválido", details);
	
	return new ResponseEntity<>(error, HttpStatus.CONFLICT);
}
	
}
