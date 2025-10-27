package com.microservicio.padron.config;


import java.io.IOException;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.microservicio.padron.entidades.Ciudadano;
import com.microservicio.padron.repository.PadronRepository;
import com.microservicio.padron.security.JwtService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component

public class JwtAuthenticationFilter extends OncePerRequestFilter {

	private final JwtService jwtService;
    private final PadronRepository padronRepository;
    

    public JwtAuthenticationFilter(JwtService jwtService, PadronRepository padronRepository) {
        
        this.jwtService = jwtService;
        this.padronRepository = padronRepository;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");
        String token = null;

        if (StringUtils.hasText(authHeader) && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);
        }

        if (token != null) {
            String dni = jwtService.extractDni(token);

            
            Ciudadano ciudadano = padronRepository.findByDni(dni).orElse(null); 

            if (ciudadano != null && jwtService.isTokenValid(token, ciudadano)) {
                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                        ciudadano,
                        null,
                        List.of(new SimpleGrantedAuthority("CIUDADANO"))
                );
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }

        filterChain.doFilter(request, response);
    }
}