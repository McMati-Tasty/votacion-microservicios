package com.microservicio.votacion.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultadoCargoDto {
    
    
    private String nombreCompleto; 
    
   
    private String partido;
    
  
    private long totalVotos;
}