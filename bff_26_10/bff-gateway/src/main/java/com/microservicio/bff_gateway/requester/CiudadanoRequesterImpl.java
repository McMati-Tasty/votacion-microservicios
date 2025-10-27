package com.microservicio.bff_gateway.requester;

import com.microservicio.bff_gateway.dto.DatosCiudadanoBffDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

@Service
public class CiudadanoRequesterImpl implements CiudadanoRequester {

    private final RestTemplate restTemplate;
    private final String baseUrl;

    public CiudadanoRequesterImpl(RestTemplate restTemplate,
                                  @Value("${microservicios.url.padron}") String baseUrl) {
        this.restTemplate = restTemplate;
        this.baseUrl = baseUrl;
    }

    @Override
    public List<DatosCiudadanoBffDto> getAll() {
        
        ResponseEntity<DatosCiudadanoBffDto[]> response = restTemplate.getForEntity(
                baseUrl + "/api/padron/datos",  
                DatosCiudadanoBffDto[].class
        );

        
        return Arrays.stream(response.getBody())
                .map(c -> {
                    DatosCiudadanoBffDto dto = new DatosCiudadanoBffDto();
                    dto.setNombre(c.getNombre());
                    dto.setApellido(c.getApellido());
                    dto.setDni(c.getDni());
                    dto.setEmail(c.getEmail());
                    dto.setNombreMesa(c.getNombreMesa());
                    return dto;
                })
                .toList();
    }
}