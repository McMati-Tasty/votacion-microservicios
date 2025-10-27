package com.microservicio.bff_gateway.requester;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.microservicio.bff_gateway.dto.OpcionAdminDto;
import com.microservicio.bff_gateway.dto.ResultadoCargoBffDto;

@Service
public class OpcionAdminRequesterImp implements OpcionAdminRequester {

    @Value("${pathBase}")
    private String urlBase;

    @Value("${microservicios.url.votacion}")
    private String votacionServiceUrl;

    private final RestTemplate restTemplate;
    private final String pathOpciones = "/api/opciones-admin";

    public OpcionAdminRequesterImp(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    
    @Override
    public List<OpcionAdminDto> listarOpciones(String token) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token); 
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<OpcionAdminDto[]> response = restTemplate.exchange(
                urlBase + pathOpciones,
                HttpMethod.GET,
                entity,
                OpcionAdminDto[].class
        );

        return Arrays.asList(response.getBody());
    }

    
    

   
  
}