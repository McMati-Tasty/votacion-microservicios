package com.microservicio.bff_gateway.dto;

import lombok.Data;

@Data 
public class DatosCiudadanoBffDto {
    
    
    private String nombre;
    private String apellido;
    private String dni;
    private String email;
    private String nombreMesa; 
}