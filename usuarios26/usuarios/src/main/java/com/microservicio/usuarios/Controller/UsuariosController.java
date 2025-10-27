package com.microservicio.usuarios.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.microservicio.usuarios.dto.NewUserDto;
import com.microservicio.usuarios.dto.ResponseLoginDto;
import com.microservicio.usuarios.dto.UserDto;
import com.microservicio.usuarios.service.LoginService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;



@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth") 
public class UsuariosController {

	private final LoginService loginService;
	
	
	@RequestMapping(value="/login", method={RequestMethod.POST    } , consumes = MediaType.APPLICATION_JSON_VALUE )
	public ResponseEntity<ResponseLoginDto> login(@RequestBody UserDto userDto   ) throws Exception{
		  
		
		   ResponseLoginDto resp = loginService.login(userDto);
		
		
		return new ResponseEntity<>(resp,HttpStatus.OK);
	}
	

	@RequestMapping(value="/create", method={RequestMethod.POST    } , consumes = MediaType.APPLICATION_JSON_VALUE )
	public ResponseEntity<ResponseLoginDto> crear(@RequestBody NewUserDto newUserDto   ) throws Exception{
		  
		
		 loginService.create(newUserDto);
		
		
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	
	
	
	
	
}