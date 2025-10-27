package com.microservicio.usuarios.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.microservicio.usuarios.dto.ErrorDTO;


@ControllerAdvice
public class ErrorHandlerAdvice extends ResponseEntityExceptionHandler {

	
	

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		
		
		 List<String> errors = new ArrayList<String>();
		    for (FieldError error : ex.getBindingResult().getFieldErrors()) {
		        errors.add(error.getField() + ": " + error.getDefaultMessage());
		    }
		    for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
		        errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
		    }
		    
			ErrorDTO errorDto = new ErrorDTO(HttpStatus.BAD_REQUEST,"hubo un errore en el ms", errors);

			return new ResponseEntity<Object> (errorDto, HttpStatus.BAD_REQUEST);
		
		
	}
	
	
	
	
}