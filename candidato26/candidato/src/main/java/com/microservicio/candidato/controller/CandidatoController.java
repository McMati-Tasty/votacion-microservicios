package com.microservicio.candidato.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.microservicio.candidato.dto.CandidatoDTO;
import com.microservicio.candidato.entidades.Candidato;
import com.microservicio.candidato.service.CandidatoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/candidato")
@CrossOrigin(origins = "http://localhost:4200/", allowCredentials = "false")
public class CandidatoController {

    @Autowired
    private CandidatoService candidatoService;

    @PostMapping("/agregar")
    public ResponseEntity<Candidato> agregar(@Valid @RequestBody CandidatoDTO dto) throws Exception {
        return new ResponseEntity<>(candidatoService.agregar(dto), HttpStatus.CREATED);
    }

    @GetMapping("/listado")
    public ResponseEntity<List<Candidato>> listado() throws Exception {
        return new ResponseEntity<>(candidatoService.listado(), HttpStatus.OK);
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<Candidato> buscar(@PathVariable int id) throws Exception {
        return new ResponseEntity<>(candidatoService.buscarPorId(id), HttpStatus.OK);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable int id) throws Exception {
        candidatoService.eliminar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    
 
    @GetMapping("/validar/{id}")
    public ResponseEntity<Boolean> validarCandidato(@PathVariable int id) {
        boolean existe = candidatoService.buscarPorIdOptional(id).isPresent();
        return ResponseEntity.ok(existe);
    }

   
    @GetMapping("/votables")
    public ResponseEntity<List<Candidato>> votables() throws Exception {
        return new ResponseEntity<>(candidatoService.listarImagenes(), HttpStatus.OK);
    }
}
