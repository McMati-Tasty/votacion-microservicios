package com.microservicio.candidato.dto;

import com.microservicio.candidato.entidades.Candidato;

import com.microservicio.candidato.enums.Roles;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CandidatoDTO {
    private Integer id_candidato;

    @NotNull
    private Integer dni;
    
	@NotNull
    @Size(min = 2)
    private String nombre_candidato;

    @NotNull
    @Size(min = 2)
    private String apellido_candidato;

    private String sexo_candidato;
    private String partido;
    private String imagen_candidato;
    private Roles rol;

    public Candidato toEntity() {
        return new Candidato(dni, nombre_candidato, apellido_candidato, sexo_candidato, partido, imagen_candidato, rol);
    }
}
