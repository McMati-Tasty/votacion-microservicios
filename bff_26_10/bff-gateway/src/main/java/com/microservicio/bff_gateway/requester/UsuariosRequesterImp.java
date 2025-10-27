package com.microservicio.bff_gateway.requester;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.microservicio.bff_gateway.dto.NewUserDto;
import com.microservicio.bff_gateway.dto.ResponseLoginDto;
import com.microservicio.bff_gateway.dto.UserDto;

@Service
public class UsuariosRequesterImp implements UsuariosRequester {

    @Value("${pathBase}")  
    private String urlBase;

    private final RestTemplate restTemplate;

    private final String pathLogin = "/usuarios/login";
    private final String pathCreate = "/usuarios/create";

    public UsuariosRequesterImp(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    @Override
    public ResponseLoginDto login(UserDto userDto) throws Exception {
        HttpEntity<UserDto> entity = new HttpEntity<>(userDto);
        String path = urlBase.concat(pathLogin);
        ResponseEntity<ResponseLoginDto> response = restTemplate.exchange(
                path,
                HttpMethod.POST,
                entity,
                ResponseLoginDto.class
        );
        return response.getBody();
    }

    @Override
    public void create(NewUserDto newUserDto) throws Exception {
     
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String token = (auth != null) ? (String) auth.getPrincipal() : null;

        if (token == null) {
            throw new RuntimeException("No se encontró token en el contexto de seguridad");
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token); 
        headers.set("Content-Type", "application/json");

        HttpEntity<NewUserDto> entity = new HttpEntity<>(newUserDto, headers);
        String path = urlBase.concat(pathCreate);

        restTemplate.exchange(
                path,
                HttpMethod.POST,
                entity,
                Void.class
        );
    }
}