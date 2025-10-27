package com.microservicio.usuarios.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.microservicio.usuarios.Entidades.User;
import com.microservicio.usuarios.dto.NewUserDto;
import com.microservicio.usuarios.dto.ResponseLoginDto;
import com.microservicio.usuarios.dto.UserDto;
import com.microservicio.usuarios.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoginServiceImp implements LoginService {

	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private JwtService jwtService;
	
    private final PasswordEncoder passwordEncoder;

	
	@Override
	public ResponseLoginDto login(UserDto userDto) throws Exception {
		
		
		   authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userDto.getUsername(), userDto.getPassword()));
		
			Optional<User> useOpt =   userRepository.findByusername(userDto.getUsername());
		   
		 	String token = jwtService.getToken(useOpt.get());
		   
	   
		   return new ResponseLoginDto(token);
		   
	}

	@Override
	public void create(NewUserDto userDto) throws Exception {
		
		User user = new User();
		user.setEnabled(Boolean.TRUE);
		user.setPassword(passwordEncoder.encode(userDto.getPassword()));
		user.setUsername(userDto.getUsername());
		user.setRol(userDto.getRol());
		
			user.setEnabled(true);
		    user.setLooked(true); 
		    user.setExpired(true);
		
		userRepository.save(  user );
	
	}

	
	
	
	
}
