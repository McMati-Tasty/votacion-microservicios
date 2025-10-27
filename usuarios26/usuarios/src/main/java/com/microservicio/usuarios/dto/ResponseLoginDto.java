package com.microservicio.usuarios.dto;

import com.microservicio.usuarios.dto.ResponseLoginDto;

import lombok.Data;

@Data
public class ResponseLoginDto {

	private String token;

	public ResponseLoginDto(String token) {
		super();
		this.token = token;
	}
	
	
	
	
	
}
