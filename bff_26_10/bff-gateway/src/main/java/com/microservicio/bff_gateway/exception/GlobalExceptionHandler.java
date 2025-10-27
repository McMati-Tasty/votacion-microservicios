package com.microservicio.bff_gateway.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

import com.microservicio.bff_gateway.dto.ErrorDTO;
// Importamos StandardCharsets
import java.nio.charset.StandardCharsets;


@ControllerAdvice
public class GlobalExceptionHandler {

   
     
    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<ErrorDTO> handleHttpClientError(HttpClientErrorException e) {
        
       
        byte[] errorBody = e.getResponseBodyAsByteArray();
        String errorMessage = new String(errorBody, StandardCharsets.UTF_8);
        
       
        HttpStatus status = (HttpStatus) e.getStatusCode();
        
   
        String title = status.getReasonPhrase();

      
        ErrorDTO errorDTO = new ErrorDTO(status, title, errorMessage); 

       
        return ResponseEntity.status(status).body(errorDTO);
    }
}



