package com.microservicio.votacion.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


@Data
public class CandidatoDto {

    
    @JsonProperty("nombre_candidato")
    private String nombre;

    @JsonProperty("apellido_candidato")
    private String apellido;

    @JsonProperty("partido")
    private String partido;

  
}