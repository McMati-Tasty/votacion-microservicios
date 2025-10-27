package com.microservicio.bff_gateway.controller;


import com.microservicio.bff_gateway.dto.CandidatoDTO;
import com.microservicio.bff_gateway.requester.CandidatoRequester;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bff/candidato")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "false")
public class CandidatoBffController {

    private final CandidatoRequester candidatoRequester;

    @Autowired
    public CandidatoBffController(CandidatoRequester candidatoRequester) {
        this.candidatoRequester = candidatoRequester;
    }

    @PostMapping("/agregar")
    public ResponseEntity<CandidatoDTO> agregar(@RequestBody CandidatoDTO dto) {
        return new ResponseEntity<>(candidatoRequester.agregar(dto), HttpStatus.CREATED);
    }

    @GetMapping("/listado")
    public ResponseEntity<List<CandidatoDTO>> listado() {
        return new ResponseEntity<>(candidatoRequester.listado(), HttpStatus.OK);
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<CandidatoDTO> buscar(@PathVariable int id) {
        return new ResponseEntity<>(candidatoRequester.buscarPorId(id), HttpStatus.OK);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable int id) {
        candidatoRequester.eliminar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/votables")
    public ResponseEntity<List<CandidatoDTO>> votables() {
        return new ResponseEntity<>(candidatoRequester.votables(), HttpStatus.OK);
    }
}