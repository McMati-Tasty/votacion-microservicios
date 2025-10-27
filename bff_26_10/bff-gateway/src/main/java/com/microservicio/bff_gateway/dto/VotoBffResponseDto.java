package com.microservicio.bff_gateway.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor 
public class VotoBffResponseDto {

    
    private String mensaje; 
    
   
    private String mensajeError;

   
    public VotoBffResponseDto(String mensaje) {
        this.mensaje = mensaje;
    }
}