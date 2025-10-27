package com.microservicio.bff_gateway.dto;

import lombok.Data;

import lombok.Getter;
import lombok.EqualsAndHashCode;

import com.microservicio.bff_gateway.enums.RolesUsuarios;

@EqualsAndHashCode(callSuper=false)
@Getter
public class NewUserDto extends UserDto {
    private String lastName;
    private RolesUsuarios rol;
}   