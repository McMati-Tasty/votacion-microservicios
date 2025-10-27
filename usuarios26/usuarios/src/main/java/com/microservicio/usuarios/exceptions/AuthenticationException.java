package com.microservicio.usuarios.exceptions;

public class AuthenticationException extends org.springframework.security.core.AuthenticationException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AuthenticationException(String msg) {
		super(msg);
		// TODO Auto-generated constructor stub
	}

}
