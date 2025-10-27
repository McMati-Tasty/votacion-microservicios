package com.microservicio.bff_gateway.controller;

import java.util.List;
import java.util.stream.Collectors; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus; 
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping; 
import org.springframework.web.bind.annotation.RequestBody; 
import org.springframework.web.bind.annotation.RequestHeader; 
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservicio.bff_gateway.dto.CiudadanoLoginBffRequestPadron;
import com.microservicio.bff_gateway.dto.CiudadanoLoginBffResponsePadron;
import com.microservicio.bff_gateway.dto.DatosCiudadanoBffDto;
import com.microservicio.bff_gateway.requester.CiudadanoRequester; // Lo mantenemos por el método listado
import com.microservicio.bff_gateway.service.PadronService;

@RestController
@RequestMapping("/bff/padron")
public class PadronBffController {

    @Autowired
    private PadronService padronService; 

   
    private final CiudadanoRequester ciudadanoRequester;

    @Autowired
    public PadronBffController(CiudadanoRequester ciudadanoRequester, PadronService padronService) {
        this.ciudadanoRequester = ciudadanoRequester;
        this.padronService = padronService; 
    }

   
    @PostMapping("/login")
    public ResponseEntity<CiudadanoLoginBffResponsePadron> loginCiudadano(@RequestBody CiudadanoLoginBffRequestPadron loginRequest) {
        CiudadanoLoginBffResponsePadron response = padronService.loginCiudadano(loginRequest);
        return ResponseEntity.ok(response);
    }

   
    @PostMapping("/verificarToken") 
    public ResponseEntity<CiudadanoLoginBffResponsePadron> verificarTokenCiudadano(
            @RequestBody CiudadanoLoginBffRequestPadron verificarRequest
    ) {
       
        CiudadanoLoginBffResponsePadron response = padronService.verificarToken(verificarRequest);

       
        return ResponseEntity.ok(response);
    }

   
    @GetMapping("/listado")
    public ResponseEntity<List<DatosCiudadanoBffDto>> listado() {
       
        List<DatosCiudadanoBffDto> lista = ciudadanoRequester.getAll();
        return ResponseEntity.ok(lista);
    }
}

