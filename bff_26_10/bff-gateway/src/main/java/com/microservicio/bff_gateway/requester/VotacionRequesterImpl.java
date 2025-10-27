package com.microservicio.bff_gateway.requester;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.microservicio.bff_gateway.dto.VotoBffRequestDto;
import com.microservicio.bff_gateway.dto.VotoBffResponseDto;

import com.microservicio.bff_gateway.dto.ResultadoCargoBffDto;

@Component
public class VotacionRequesterImpl implements VotacionRequester {

    @Autowired
    private RestTemplate restTemplate;

    
    @Value("${microservicios.url.votacion}") 
    private String votacionServiceUrl;

    @Override
    public VotoBffResponseDto votar(VotoBffRequestDto votoRequest) {
        
        
        String url = votacionServiceUrl + "/votos/votar";
        VotoBffResponseDto responseDto = new VotoBffResponseDto();

        try {
            
            String respuestaExito = restTemplate.postForObject(url, votoRequest, String.class);
            
            responseDto.setMensaje(respuestaExito); 
            return responseDto;

        } catch (HttpClientErrorException e) { 
           
         
            responseDto.setMensajeError(e.getResponseBodyAsString());
            return responseDto;

        } catch (Exception e) {
            
            System.err.println("Error al contactar microservicio de votación: " + e.getMessage());
            responseDto.setMensajeError("Error interno al registrar el voto.");
            return responseDto;
        }
    }
    
    
    @Override
    public List<ResultadoCargoBffDto> getResumenPresidente() {
        
        String url = votacionServiceUrl + "/votos/resumen/presidente";
        return getResumenForCargo(url);
    }

    @Override
    public List<ResultadoCargoBffDto> getResumenVicepresidente() {
        
        String url = votacionServiceUrl + "/votos/resumen/vicepresidente";
        return getResumenForCargo(url);
    }

    @Override
    public List<ResultadoCargoBffDto> getResumenGobernador() {
        
        String url = votacionServiceUrl + "/votos/resumen/gobernador";
        return getResumenForCargo(url);
    }

   
    private List<ResultadoCargoBffDto> getResumenForCargo(String url) {
        try {
            ResponseEntity<ResultadoCargoBffDto[]> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    null, 
                    ResultadoCargoBffDto[].class 
            );
    
            return Arrays.asList(response.getBody());

        } catch (Exception e) {
            System.err.println("Error al obtener resumen de votos desde " + url + ": " + e.getMessage());
            return List.of(); 
        }
    }
    
    
}