package com.microservicio.usuarios.dto;

import com.microservicio.usuarios.enums.Roles;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode(callSuper=false)
@Getter
public class NewUserDto extends UserDto {

	private String lastName;
	
	private Roles rol;
		
	
	
}
