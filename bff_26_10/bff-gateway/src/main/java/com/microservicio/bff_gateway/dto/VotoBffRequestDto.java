package com.microservicio.bff_gateway.dto;

import lombok.Data;

@Data
public class VotoBffRequestDto {

    private String tokenDeVoto; 
    
    private int idPresidente;
    private int idVicepresidente;
    private int idGobernador;
}