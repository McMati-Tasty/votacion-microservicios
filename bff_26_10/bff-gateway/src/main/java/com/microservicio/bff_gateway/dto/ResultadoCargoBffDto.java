package com.microservicio.bff_gateway.dto;

import lombok.Data;


@Data
public class ResultadoCargoBffDto {

    
    private String nombreCompleto; 
    private String partido;
    private long totalVotos;
}