package com.microservicio.usuarios.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.microservicio.usuarios.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class AppConfig {

    private final UserRepository userRepository;

	
	@Bean
	public  AuthenticationManager customAuthManager(AuthenticationConfiguration config ) throws Exception {
		 return config.getAuthenticationManager();
	}	
	
    @Bean
    public AuthenticationProvider authenticationProvider()
    {
        DaoAuthenticationProvider authenticationProvider= new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailService());
        authenticationProvider.setPasswordEncoder(encode());
        return authenticationProvider;
    }
	
	@Bean
	public BCryptPasswordEncoder encode() {
	    return new BCryptPasswordEncoder();
	}
	
    @Bean
    public UserDetailsService userDetailService() {
        return username -> userRepository.findByusername(username)
        .orElseThrow(()-> new UsernameNotFoundException("usuario no encontrado"));
    }
	
}
