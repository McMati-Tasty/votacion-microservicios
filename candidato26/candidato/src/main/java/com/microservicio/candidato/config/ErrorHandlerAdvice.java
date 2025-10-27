package com.microservicio.candidato.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.microservicio.candidato.dto.ErrorDTO;
import com.microservicio.candidato.exception.CandidatoException;

@ControllerAdvice
public class ErrorHandlerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ CandidatoException.class })
    public ResponseEntity<ErrorDTO> handleCandidatoException(CandidatoException exception) {
        ErrorDTO errorDto = new ErrorDTO(
            HttpStatus.BAD_REQUEST,
            "Error en el microservicio de Candidato",
            exception.getMessage()
        );
        return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {

        List<String> errors = new ArrayList<>();
        ex.getBindingResult().getFieldErrors()
            .forEach(err -> errors.add(err.getField() + ": " + err.getDefaultMessage()));
        ex.getBindingResult().getGlobalErrors()
            .forEach(err -> errors.add(err.getObjectName() + ": " + err.getDefaultMessage()));

        ErrorDTO errorDto = new ErrorDTO(
            HttpStatus.BAD_REQUEST,
            "Error de validación en el microservicio de Candidato",
            errors
        );

        return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
    }
}
