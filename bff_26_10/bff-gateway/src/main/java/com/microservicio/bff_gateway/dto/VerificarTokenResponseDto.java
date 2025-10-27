package com.microservicio.bff_gateway.dto;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VerificarTokenResponseDto {
    private String tokenDeVoto; // El UUID generado
    private String mensajeError;
}