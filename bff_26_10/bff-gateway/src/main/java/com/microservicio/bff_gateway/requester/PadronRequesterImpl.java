package com.microservicio.bff_gateway.requester;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.microservicio.bff_gateway.dto.CiudadanoLoginBffRequestPadron;
import com.microservicio.bff_gateway.dto.CiudadanoLoginBffResponsePadron;
import com.microservicio.bff_gateway.dto.VerificarTokenRequestDto; // <-- DTO NUEVO
import com.microservicio.bff_gateway.dto.VerificarTokenResponseDto; // <-- DTO NUEVO

@Component
public class PadronRequesterImpl implements PadronRequester {

    @Autowired
    private RestTemplate restTemplate; 

    @Value("${microservicios.url.padron}")
    private String padronServiceUrl;
    @Override
    public CiudadanoLoginBffResponsePadron loginCiudadano(CiudadanoLoginBffRequestPadron loginRequest) {
        
        String url = padronServiceUrl + "/ciudadano/login";
        return restTemplate.postForObject(url, loginRequest, CiudadanoLoginBffResponsePadron.class);
    }

    
    @Override
    public CiudadanoLoginBffResponsePadron verificarToken(CiudadanoLoginBffRequestPadron verificarRequest) {
        
        String url = padronServiceUrl + "/api/padron/validar-y-generar-token-voto";

       
        VerificarTokenRequestDto payloadParaPadron = new VerificarTokenRequestDto();
        payloadParaPadron.setDni(verificarRequest.getDni());
        payloadParaPadron.setToken(verificarRequest.getToken()); // Mandamos DNI y JWT

     
        CiudadanoLoginBffResponsePadron responseParaAngular = new CiudadanoLoginBffResponsePadron();

        try {
            
            ResponseEntity<VerificarTokenResponseDto> respuestaPadron = restTemplate.postForEntity(
                url,
                payloadParaPadron,
                VerificarTokenResponseDto.class
            );

            
            if (respuestaPadron.getStatusCode().is2xxSuccessful() && respuestaPadron.getBody() != null) {
                VerificarTokenResponseDto body = respuestaPadron.getBody();
                responseParaAngular.setTokenDeVoto(body.getTokenDeVoto()); 
                responseParaAngular.setMensajeError(body.getMensajeError());
                System.out.println("BFF: Verificación OK, recibido UUID: " + body.getTokenDeVoto()); 
            } else {
                 responseParaAngular.setMensajeError("Error inesperado al verificar token (Respuesta no OK).");
                 System.err.println("BFF: Error inesperado de Padron, status: " + respuestaPadron.getStatusCode()); // Log
            }

        } catch (HttpClientErrorException e) {
             
             System.err.println("BFF: Error " + e.getStatusCode() + " de Padron al verificar token."); // Log
             try {
               
                 VerificarTokenResponseDto errorBody = e.getResponseBodyAs(VerificarTokenResponseDto.class);
                 responseParaAngular.setMensajeError(errorBody != null ? errorBody.getMensajeError() : "Token inválido o expirado.");
             } catch (Exception parseEx) {
                 responseParaAngular.setMensajeError("Token inválido o expirado.");
             }

        } catch (Exception e) {
            
            System.err.println("BFF: Error GENERAL al llamar a Padron verificar token: " + e.getMessage()); // Log
            responseParaAngular.setMensajeError("Error interno del servidor al verificar el token.");
        }
        
        return responseParaAngular; 
    }
}