package com.microservicio.bff_gateway.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservicio.bff_gateway.dto.VotoBffRequestDto;
import com.microservicio.bff_gateway.dto.VotoBffResponseDto;
import com.microservicio.bff_gateway.service.VotacionService;

import com.microservicio.bff_gateway.dto.ResultadoCargoBffDto;

@RestController
@RequestMapping("/bff/votacion")
public class VotacionBffController {

    @Autowired
    private VotacionService votacionService;

    @PostMapping("/votar")
    public ResponseEntity<VotoBffResponseDto> votar(@RequestBody VotoBffRequestDto votoRequest) {
        
        VotoBffResponseDto response = votacionService.votar(votoRequest);

       
        if (response.getMensajeError() != null && !response.getMensajeError().isBlank()) {
            
            
            if (response.getMensajeError().contains("Token")) {
                 return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
            }
           
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

      
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    
    
    @GetMapping("/resumen/presidente")
    public ResponseEntity<List<ResultadoCargoBffDto>> getResumenPresidente() {
        List<ResultadoCargoBffDto> resumen = votacionService.getResumenPresidente();
        return ResponseEntity.ok(resumen);
    }

    @GetMapping("/resumen/vicepresidente")
    public ResponseEntity<List<ResultadoCargoBffDto>> getResumenVicepresidente() {
        List<ResultadoCargoBffDto> resumen = votacionService.getResumenVicepresidente();
        return ResponseEntity.ok(resumen);
    }

    @GetMapping("/resumen/gobernador")
    public ResponseEntity<List<ResultadoCargoBffDto>> getResumenGobernador() {
        List<ResultadoCargoBffDto> resumen = votacionService.getResumenGobernador();
        return ResponseEntity.ok(resumen);
    }
    
    
}