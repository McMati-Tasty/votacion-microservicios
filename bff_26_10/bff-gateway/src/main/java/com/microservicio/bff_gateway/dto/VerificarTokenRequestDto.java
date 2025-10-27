package com.microservicio.bff_gateway.dto;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VerificarTokenRequestDto {
    private String dni;
    private String token; // El JWT del email
}