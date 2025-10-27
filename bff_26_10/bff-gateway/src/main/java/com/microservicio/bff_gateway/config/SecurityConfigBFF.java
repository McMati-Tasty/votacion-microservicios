package com.microservicio.bff_gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity; 
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.RequiredArgsConstructor; // Asegúrate de que estés usando Lombok

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor 
public class SecurityConfigBFF {

    private final JwtAuthenticationFilterBFF jwtFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) 
            .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(auth -> auth
                
             
                
                
                .requestMatchers(HttpMethod.POST, "/bff/padron/login").permitAll()
                
              
                .requestMatchers(HttpMethod.POST, "/bff/padron/verificarToken").permitAll() 
                
                
                .requestMatchers(HttpMethod.POST, "/usuarios/login").permitAll()
                .requestMatchers(HttpMethod.POST, "/bff/votacion/votar").permitAll()
                .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/bff/candidato/**").permitAll()

               
                .requestMatchers(HttpMethod.POST, "/usuarios/create").hasAuthority("ADMIN")
                .requestMatchers("/api/opciones-admin/**").hasAuthority("ADMIN") 
                .requestMatchers(HttpMethod.GET, "/bff/padron/listado").hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.POST, "/bff/candidato/agregar").hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/bff/candidato/eliminar/**").hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.GET, "/bff/votacion/resumen/**").hasAuthority("ADMIN")

               
                .anyRequest().authenticated() 
            )
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
