package com.microservicio.candidato.dto;

import java.util.List;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class ErrorDTO {

    private int status;
    private String title;
    private String message;
    private List<String> errors;

    public ErrorDTO(HttpStatus httpStatus, String title, String message) {
        this.status = httpStatus.value();
        this.title = title;
        this.message = message;
    }

    public ErrorDTO(HttpStatus httpStatus, String title, List<String> errors) {
        this.status = httpStatus.value();
        this.title = title;
        this.errors = errors;
    }
}
