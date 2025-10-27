package com.microservicio.votacion.service;

import java.util.List;
import java.util.Map;
import java.util.ArrayList; // <-- ¡NUEVO IMPORT!
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.microservicio.votacion.dto.VotoDto;
import com.microservicio.votacion.dto.ResultadoCargoDto; // <-- ¡NUEVO IMPORT!
import com.microservicio.votacion.dto.CandidatoDto; // <-- ¡NUEVO IMPORT!
import com.microservicio.votacion.entidades.Voto;
import com.microservicio.votacion.repository.VotoRepository;
import com.microservicio.votacion.repository.VotoRepository.ConteoVotos; // <-- ¡NUEVO IMPORT!

import lombok.RequiredArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Service
@RequiredArgsConstructor
public class VotoServiceImp implements VotoService {

    private final VotoRepository votoRepository;
    private final RestTemplate restTemplate;

    @Value("${padron.service.url}") 
    private String PADRON_SERVICE_URL;

    @Value("${candidato.service.url}") 
    private String CANDIDATO_SERVICE_URL;
    

    
    
    @Override
    public void votar(VotoDto votoDto) throws Exception {
    	System.out.println("====== RECIBIDO VOTO: " + votoDto.toString() + " ======");
        
       
        if (votoDto.getIdPresidente() != null && votoDto.getIdPresidente() != 0 && !validarCandidato(votoDto.getIdPresidente())) {
            throw new Exception("El ID del Presidente no es válido.");
        }
        if (votoDto.getIdVicepresidente() != null && votoDto.getIdVicepresidente() != 0 && !validarCandidato(votoDto.getIdVicepresidente())) {
            throw new Exception("El ID del Vicepresidente no es válido.");
        }
        if (votoDto.getIdGobernador() != null && votoDto.getIdGobernador() != 0 && !validarCandidato(votoDto.getIdGobernador())) {
            throw new Exception("El ID del Gobernador no es válido.");
        }

       
        TokenRequestDto tokenRequest = new TokenRequestDto(votoDto.getTokenDeVoto());
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<TokenRequestDto> requestEntity = new HttpEntity<>(tokenRequest, headers);

        String urlConsumoPadron = PADRON_SERVICE_URL + "/api/padron/internal/consumir-token";

        try {
           
            ResponseEntity<Boolean> response = restTemplate.postForEntity(
                urlConsumoPadron, 
                requestEntity, 
                Boolean.class 
            );

           
            if (response.getStatusCode() == HttpStatus.OK && Boolean.TRUE.equals(response.getBody())) {
                
             
                Voto nuevoVoto = new Voto();
                
            
                
             
                
           
             nuevoVoto.setIdPresidente(votoDto.getIdPresidente());
             nuevoVoto.setIdVicepresidente(votoDto.getIdVicepresidente());
             nuevoVoto.setIdGobernador(votoDto.getIdGobernador());
                     
             votoRepository.save(nuevoVoto);
            
            } else {
                throw new Exception("Error inesperado al validar el voto: " + response.getStatusCode());
            }

        } catch (HttpClientErrorException e) {
           
            if (e.getStatusCode() == HttpStatus.UNAUTHORIZED) {
                throw new Exception("Token de voto inválido o ya utilizado.");
            } else {
                throw new Exception("Error de comunicación con el padrón: " + e.getMessage());
            }
        }
    }

  
     
    private boolean validarCandidato(Integer id) {
        if (id == null) return false;
        
        String urlValidacionCandidato = CANDIDATO_SERVICE_URL + "/candidato/validar/" + id;
        
        try {
            ResponseEntity<Boolean> response = restTemplate.getForEntity(urlValidacionCandidato, Boolean.class);
            return response.getStatusCode() == HttpStatus.OK && Boolean.TRUE.equals(response.getBody());
            
        } catch (Exception e) {
            System.err.println("Error validando candidato ID " + id + ": " + e.getMessage());
            return false;
        }
    }


    @Override
    public List<Voto> listarVotos() {
        return votoRepository.findAll();
    }

    @Override
    public Voto buscarPorId(int id) throws Exception {
        return votoRepository.findById(id)
                .orElseThrow(() -> new Exception("Voto no encontrado"));
    }
   

    @Override
    public List<ResultadoCargoDto> getResumenPresidente() {
      
        List<ConteoVotos> conteo = votoRepository.contarPorPresidente();
       
        return enriquecerResultados(conteo);
    }

    @Override
    public List<ResultadoCargoDto> getResumenVicepresidente() {
        List<ConteoVotos> conteo = votoRepository.contarPorVicepresidente();
        return enriquecerResultados(conteo);
    }

    @Override
    public List<ResultadoCargoDto> getResumenGobernador() {
        List<ConteoVotos> conteo = votoRepository.contarPorGobernador();
        return enriquecerResultados(conteo);
    }


 
    private List<ResultadoCargoDto> enriquecerResultados(List<ConteoVotos> conteo) {
        
        List<ResultadoCargoDto> resultados = new ArrayList<>();
        
        
        String urlBaseCandidato = CANDIDATO_SERVICE_URL + "/candidato/buscar/";

        for (ConteoVotos voto : conteo) {
            
         
            if (voto.getCandidatoId() == 0) {
                resultados.add(new ResultadoCargoDto("Voto en Blanco", "-", voto.getTotalVotos()));
                continue;
            }

            try {
              
                CandidatoDto candidato = restTemplate.getForObject(
                    urlBaseCandidato + voto.getCandidatoId(), 
                    CandidatoDto.class
                );

                if (candidato != null) {
                    
                    String nombreCompleto = candidato.getNombre() + " " + candidato.getApellido();
                    
                    resultados.add(new ResultadoCargoDto(
                        nombreCompleto,
                        candidato.getPartido(),
                        voto.getTotalVotos()
                    ));
                }
            } catch (Exception e) {
              
                System.err.println("Error al buscar candidato ID " + voto.getCandidatoId() + ": " + e.getMessage());
                resultados.add(new ResultadoCargoDto(
                    "Candidato ID " + voto.getCandidatoId() + " (No encontrado)",
                    "?",
                    voto.getTotalVotos()
                ));
            }
        }
        
        return resultados;
    }

    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    static class TokenRequestDto {
        private String token;
    }
}