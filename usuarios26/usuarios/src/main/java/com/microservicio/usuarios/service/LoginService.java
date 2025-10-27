package com.microservicio.usuarios.service;

import com.microservicio.usuarios.dto.NewUserDto;
import com.microservicio.usuarios.dto.ResponseLoginDto;
import com.microservicio.usuarios.dto.UserDto;

public interface LoginService {

	
	public ResponseLoginDto login ( UserDto userDto   ) throws Exception;   
	
	public void create ( NewUserDto userDto   ) throws Exception;   
	

}
