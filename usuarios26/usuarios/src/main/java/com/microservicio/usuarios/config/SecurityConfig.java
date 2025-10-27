package com.microservicio.usuarios.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservicio.usuarios.dto.ErrorDTO;
import com.microservicio.usuarios.repository.UserRepository;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {


	private final AutProvider autProvider;
	
    private final JwtAuthenticationFilter authenticationFilter;
 
	
	@Bean
	public SecurityFilterChain securityFilterCharin(HttpSecurity http ) throws Exception{
		
		http.authorizeHttpRequests(    
			t-> t
			.requestMatchers("/swagger-ui/**", "/swagger-resources/**" ,"/webjars/**","/v3/api-docs/**").permitAll()
			.requestMatchers(  HttpMethod.POST,"/persona/agregar").hasAuthority("ADMIN")
			.requestMatchers(  HttpMethod.GET, "/persona/listado").hasAnyAuthority("USER","ADMIN")
			.requestMatchers(  "/usuarios/login").permitAll()	
			.requestMatchers(  HttpMethod.POST,"/usuarios/create").hasAnyAuthority("ADMIN")
			.anyRequest().authenticated()
			)
		.authenticationProvider(autProvider)
		.csrf(csrf -> csrf.disable())
		.sessionManagement(sessionManager -> sessionManager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class)
			.exceptionHandling(exh -> exh.authenticationEntryPoint(
				   
					(request, response, ex) -> {
						response.setStatus(HttpStatus.UNAUTHORIZED.value());
						response.setContentType("application/json");
						
						ErrorDTO errorDTO = new ErrorDTO(HttpStatus.UNAUTHORIZED, "No autorizado", ex.getMessage());

					        ObjectMapper mapper = new ObjectMapper();
					        
					        String jsonError = mapper.writeValueAsString(errorDTO);

					        response.getWriter().write(jsonError);
						
						
						
						
					}
				));
			
		
		
		return http.build();
	}
	
	
	
}
