package com.microservicio.bff_gateway.dto;

import lombok.Data;
import lombok.NoArgsConstructor;   
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor    
@AllArgsConstructor   
public class CiudadanoLoginBffRequestPadron {
    
    private String dni;
    
   
    private String token; 
    
}