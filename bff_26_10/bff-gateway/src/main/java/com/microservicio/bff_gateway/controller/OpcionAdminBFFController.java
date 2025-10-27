package com.microservicio.bff_gateway.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.microservicio.bff_gateway.dto.OpcionAdminDto;
import com.microservicio.bff_gateway.dto.ResultadoCargoBffDto; 
import com.microservicio.bff_gateway.service.OpcionAdminService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/opciones-admin")
@RequiredArgsConstructor
public class OpcionAdminBFFController {

    private final OpcionAdminService opcionService;

    
    @GetMapping
    public ResponseEntity<List<OpcionAdminDto>> listarOpciones(@RequestHeader("Authorization") String token) throws Exception {
        List<OpcionAdminDto> opciones = opcionService.listarOpciones(token);
        return ResponseEntity.ok(opciones);
    }

   
   
   
  
    
}