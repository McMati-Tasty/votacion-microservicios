package com.microservicio.padron.controller;

import com.microservicio.padron.dto.CiudadanoDTO;
import com.microservicio.padron.dto.CiudadanoMapper;
import com.microservicio.padron.dto.DatosCiudadanoDto;
import com.microservicio.padron.dto.TokenRequestDto;
import com.microservicio.padron.dto.VerificarTokenRequestDto;  // <-- NUEVO DTO
import com.microservicio.padron.dto.VerificarTokenResponseDto; // <-- NUEVO DTO
import com.microservicio.padron.entidades.Ciudadano;
import com.microservicio.padron.service.PadronService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus; // <-- Importar HttpStatus
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/padron")
@CrossOrigin(origins = "*") 
@RequiredArgsConstructor
public class PadronController {

    private final PadronService padronService;
    private final CiudadanoMapper mapper;


    @GetMapping("/datos")
    public ResponseEntity<List<DatosCiudadanoDto>> getAllDatosCiudadanos() {
        List<DatosCiudadanoDto> lista = padronService.getAll().stream()
                .map(mapper::toDatosCiudadanoDto).collect(Collectors.toList());
        return ResponseEntity.ok(lista);
    }
  

   
    @GetMapping("/habilitados")
    public ResponseEntity<List<CiudadanoDTO>> getHabilitados() {
         List<CiudadanoDTO> habilitados = padronService.getHabilitados().stream()
                 .map(mapper::toDTO).collect(Collectors.toList());
        return ResponseEntity.ok(habilitados);
    }
   
    @GetMapping("/verificar/{dni}")
    public ResponseEntity<CiudadanoDTO> verificarCiudadano(@PathVariable String dni) {
        try {
            Ciudadano c = padronService.verificarPorDni(dni); 
            return ResponseEntity.ok(mapper.toDTO(c));
        } catch (RuntimeException e) {
           
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    
    @PostMapping("/validar-y-generar-token-voto")
    public ResponseEntity<VerificarTokenResponseDto> validarYGenerarToken(
            @RequestBody VerificarTokenRequestDto request
    ) {
        try {
          
            String tokenVotoUuid = padronService.validarJwtYGenerarTokenVoto(request.getDni(), request.getToken());

          
            VerificarTokenResponseDto response = new VerificarTokenResponseDto();
            response.setTokenDeVoto(tokenVotoUuid);
            return ResponseEntity.ok(response);

        } catch (RuntimeException e) {
         
            VerificarTokenResponseDto errorResponse = new VerificarTokenResponseDto();
            errorResponse.setMensajeError(e.getMessage());
            
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
        } catch (Exception e) {
             
            System.err.println("Error inesperado en validarYGenerarToken: " + e.getMessage());
            VerificarTokenResponseDto errorResponse = new VerificarTokenResponseDto();
            errorResponse.setMensajeError("Error interno del servidor al validar token.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }


   
    @PostMapping("/internal/consumir-token")
    public ResponseEntity<Boolean> consumirToken(@RequestBody TokenRequestDto request) {
    	System.out.println("PADRON: Recibida petición para consumir token: " + request.getToken());
        boolean esValido = padronService.consumirToken(request.getToken());
        if (esValido) {
            return ResponseEntity.ok(true);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(false);
        }
    }

  
}