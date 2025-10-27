package com.microservicio.bff_gateway.dto;

import lombok.Data;

@Data
public class OpcionAdminDto {
    private int id;
    private String titulo;
    private String descripcion;
    private String imagenUrl;
    private String enlaceUrl;
}