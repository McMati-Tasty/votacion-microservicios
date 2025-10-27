package com.microservicio.votacion.dto;

import lombok.Data;

@Data
public class VotoDto {

    private String tokenDeVoto;
    

    
    private Integer idPresidente;
    private Integer idVicepresidente;
    private Integer idGobernador;
}