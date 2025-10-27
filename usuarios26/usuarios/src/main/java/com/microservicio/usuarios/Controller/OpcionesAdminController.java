package com.microservicio.usuarios.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservicio.usuarios.dto.ErrorDTO;
import com.microservicio.usuarios.Entidades.OpcionAdmin;
import com.microservicio.usuarios.service.OpcionAdminService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/opciones-admin")
@RequiredArgsConstructor
public class OpcionesAdminController {

    private final OpcionAdminService service;

    // Solo usuarios con rol ADMIN pueden acceder
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public ResponseEntity<?> listarOpciones() {
        try {
            List<OpcionAdmin> opciones = service.listarOpciones();
            return ResponseEntity.ok(opciones);
        } catch (Exception e) {
            ErrorDTO error = new ErrorDTO(HttpStatus.INTERNAL_SERVER_ERROR, 
                                          "Error interno", 
                                          List.of(e.getMessage()));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }
}