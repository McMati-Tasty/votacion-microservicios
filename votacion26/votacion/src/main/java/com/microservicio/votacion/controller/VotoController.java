package com.microservicio.votacion.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.microservicio.votacion.dto.VotoDto;
import com.microservicio.votacion.dto.RegistrarVotoResponse;
import com.microservicio.votacion.dto.ResultadoCargoDto; // <-- ¡CAMBIO DE IMPORT!
import com.microservicio.votacion.entidades.Voto;
import com.microservicio.votacion.service.VotoService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/votos")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class VotoController {

    private final VotoService votoService;

    
    @PostMapping("/votar")
    public ResponseEntity<?> votar(@RequestBody VotoDto votoDto) { 
        try {
            votoService.votar(votoDto); 

         
            RegistrarVotoResponse response = new RegistrarVotoResponse("Voto registrado exitosamente");
            
            return new ResponseEntity<>(response, HttpStatus.CREATED);

        } catch (Exception e) {
           
            if (e.getMessage().contains("Token")) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
            }
           
            RegistrarVotoResponse errorResponse = new RegistrarVotoResponse("Error: " + e.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }
    }
    
    
    @GetMapping("/listar")
    public ResponseEntity<List<Voto>> listarVotos() {
        try {
            List<Voto> votos = votoService.listarVotos();
            return new ResponseEntity<>(votos, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable int id) {
        try {
            Voto voto = votoService.buscarPorId(id);
            return new ResponseEntity<>(voto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    

    
    

    @GetMapping("/resumen/presidente")
    public ResponseEntity<List<ResultadoCargoDto>> resumenPresidente() {
        try {
            List<ResultadoCargoDto> resumen = votoService.getResumenPresidente();
            return new ResponseEntity<>(resumen, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/resumen/vicepresidente")
    public ResponseEntity<List<ResultadoCargoDto>> resumenVicepresidente() {
        try {
            List<ResultadoCargoDto> resumen = votoService.getResumenVicepresidente();
            return new ResponseEntity<>(resumen, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/resumen/gobernador")
    public ResponseEntity<List<ResultadoCargoDto>> resumenGobernador() {
        try {
            List<ResultadoCargoDto> resumen = votoService.getResumenGobernador();
            return new ResponseEntity<>(resumen, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}