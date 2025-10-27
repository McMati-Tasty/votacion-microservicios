package com.microservicio.usuarios.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder; // <-- QUE TENGA ESTE IMPORT
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import com.microservicio.usuarios.Entidades.User;
import com.microservicio.usuarios.repository.UserRepository;

@Component
public class AutProvider implements AuthenticationProvider{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
	
		 String usuario = authentication.getName();
		 String password = authentication.getCredentials().toString();
	
		 Optional<User> userOpt = userRepository.findByusername( usuario );
		 
		 if (userOpt.isEmpty()) {
			 return null; 
		 }
		 
		 
		 if (passwordEncoder.matches(password, userOpt.get().getPassword())) {
				
				return  new UsernamePasswordAuthenticationToken(usuario, password, userOpt.get().getAuthorities()); 
		 }

		return null; 
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return true;
	}
}