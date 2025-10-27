package com.microservicio.bff_gateway.dto;


import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

@Data
public class UserDto {

  
	@JsonAlias("email") 
	private String username ; 
	private String password;
}