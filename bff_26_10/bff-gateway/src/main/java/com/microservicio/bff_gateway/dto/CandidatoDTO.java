package com.microservicio.bff_gateway.dto;

import lombok.Data;

@Data
public class CandidatoDTO {
    private Integer id_candidato;
    private Integer dni;
    private String nombre_candidato;
    private String apellido_candidato;
    private String sexo_candidato;
    private String partido;
    private String rol;
    private String imagen_candidato;
}
