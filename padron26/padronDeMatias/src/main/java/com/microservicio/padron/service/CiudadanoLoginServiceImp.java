package com.microservicio.padron.service;

import com.microservicio.padron.dto.CiudadanoLoginRequest;
import com.microservicio.padron.dto.DatosCiudadanoDto;
import com.microservicio.padron.dto.LoginResponseDto;
import com.microservicio.padron.entidades.Ciudadano;
import com.microservicio.padron.entidades.TokenVoto;
import com.microservicio.padron.repository.PadronRepository;
import com.microservicio.padron.repository.TokenVotoRepository;
import com.microservicio.padron.security.TokenVotoGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor 
public class CiudadanoLoginServiceImp implements CiudadanoService { 
    

    private final PadronRepository padronRepository;
    private final TokenVotoRepository tokenVotoRepository;
    private final TokenVotoGenerator tokenVotoGenerator;
    
    private final EmailService emailService; 


    @Override
    @Transactional
    public LoginResponseDto login(CiudadanoLoginRequest loginDTO) throws Exception {

       
        Optional<Ciudadano> optCiudadano = padronRepository.findByDni(loginDTO.getDni());
        if (optCiudadano.isEmpty()) {
            throw new Exception("Ciudadano no encontrado con DNI: " + loginDTO.getDni());
        }
        
        Ciudadano ciudadano = optCiudadano.get();

       
        if (!ciudadano.isHabilitado()) {
            throw new Exception("El ciudadano no está habilitado para votar.");
        }
        if (ciudadano.isYaVoto()) {
            throw new Exception("El ciudadano ya emitió su voto.");
        }

      
        TokenVoto tokenViejo = ciudadano.getTokenVoto();
        if (tokenViejo != null) {
            ciudadano.setTokenVoto(null); 
            tokenVotoRepository.delete(tokenViejo);
        }

       
        String tokenString = tokenVotoGenerator.generateVotoToken();
        
     
        TokenVoto nuevoToken = new TokenVoto(tokenString);
        nuevoToken.setCiudadano(ciudadano);
        
     
        tokenVotoRepository.save(nuevoToken);


     
        emailService.enviarTokenDeVoto(ciudadano, tokenString);


        
        DatosCiudadanoDto datosCiudadano = new DatosCiudadanoDto();
        datosCiudadano.setNombre(ciudadano.getNombre());
        datosCiudadano.setApellido(ciudadano.getApellido());
        datosCiudadano.setDni(ciudadano.getDni());
        datosCiudadano.setEmail(ciudadano.getEmail());
        
        if (ciudadano.getMesa() != null) {
            datosCiudadano.setNombreMesa("Mesa " + ciudadano.getMesa().getId()); // Ejemplo
        } else {
            datosCiudadano.setNombreMesa("Sin mesa asignada");
        }
        
        
        LoginResponseDto response = new LoginResponseDto();
        
       
        response.setDatosCiudadano(datosCiudadano);

        return response; 
    }
}

