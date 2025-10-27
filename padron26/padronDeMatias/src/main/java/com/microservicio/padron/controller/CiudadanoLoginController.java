package com.microservicio.padron.controller;

import com.microservicio.padron.dto.CiudadanoLoginRequest;
import com.microservicio.padron.dto.LoginResponseDto; 
import com.microservicio.padron.service.CiudadanoService; 
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ciudadano") 
@RequiredArgsConstructor
public class CiudadanoLoginController {

    
    private final CiudadanoService ciudadanoService; 

  
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody CiudadanoLoginRequest loginDTO) {
        
        try {
           
            LoginResponseDto response = ciudadanoService.login(loginDTO);
            
          
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            
            //  si el servicio lanzó una excepción (DNI no existe, ya votó, etc.)
            // la capturamos aca y devolvemos un error 401 (No Autorizado)
            // con el mensaje de la excepción (ej. "El ciudadano ya emitió su voto.")
            return ResponseEntity.status(401).body(e.getMessage());
        }
    }
}