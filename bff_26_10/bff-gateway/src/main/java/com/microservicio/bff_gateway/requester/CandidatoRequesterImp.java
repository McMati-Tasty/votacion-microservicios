package com.microservicio.bff_gateway.requester;

import com.microservicio.bff_gateway.dto.CandidatoDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class CandidatoRequesterImp implements CandidatoRequester {

    private final RestTemplate restTemplate;
    private final String baseUrl;

    public CandidatoRequesterImp(RestTemplate restTemplate,
                                 @Value("${microservicio.candidato.url}") String baseUrl) {
        this.restTemplate = restTemplate;
        this.baseUrl = baseUrl;
    }

    @Override
    public CandidatoDTO agregar(CandidatoDTO dto) {
        HttpEntity<CandidatoDTO> request = new HttpEntity<>(dto);
        ResponseEntity<CandidatoDTO> response = restTemplate.exchange(
                baseUrl + "/candidato/agregar",
                HttpMethod.POST,
                request,
                CandidatoDTO.class
        );
        return response.getBody();
    }

    @Override
    public List<CandidatoDTO> listado() {
        ResponseEntity<CandidatoDTO[]> response = restTemplate.getForEntity(
                baseUrl + "/candidato/listado",
                CandidatoDTO[].class
        );
        return Arrays.asList(response.getBody());
    }

    @Override
    public CandidatoDTO buscarPorId(int id) {
        return restTemplate.getForObject(baseUrl + "/candidato/buscar/" + id, CandidatoDTO.class);
    }

    @Override
    public void eliminar(int id) {
        restTemplate.delete(baseUrl + "/candidato/eliminar/" + id);
    }

    @Override
    public List<CandidatoDTO> votables() {
        ResponseEntity<CandidatoDTO[]> response = restTemplate.getForEntity(
                baseUrl + "/candidato/votables",
                CandidatoDTO[].class
        );
        return Arrays.asList(response.getBody());
    }
}