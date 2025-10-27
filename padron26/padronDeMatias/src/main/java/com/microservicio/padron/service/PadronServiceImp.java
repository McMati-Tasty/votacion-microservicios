package com.microservicio.padron.service;

import com.microservicio.padron.entidades.Ciudadano;
import com.microservicio.padron.entidades.TokenVoto;
import com.microservicio.padron.repository.PadronRepository;
import com.microservicio.padron.repository.TokenVotoRepository;
import com.microservicio.padron.security.JwtService; // <-- Asegurate que el import sea correcto

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID; // <-- Import para generar UUID

@Service
@RequiredArgsConstructor
public class PadronServiceImp implements PadronService {

 
    private final PadronRepository ciudadanoRepository;
    private final TokenVotoRepository tokenVotoRepository;
    private final EmailService emailService;
    private final JwtService jwtService; 


    @Override
    public List<Ciudadano> getAll() {
        return ciudadanoRepository.findAll();
    }

    @Override
    public Ciudadano getById(Long id) {
        return ciudadanoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ciudadano no encontrado con id: " + id));
    }

    @Override
    public Ciudadano create(Ciudadano ciudadano) {
        return ciudadanoRepository.save(ciudadano);
    }

    @Override
    public Ciudadano update(Long id, Ciudadano ciudadano) {
        Ciudadano existente = getById(id);
      
        existente.setNombre(ciudadano.getNombre());
        existente.setApellido(ciudadano.getApellido());
        existente.setDni(ciudadano.getDni());
      
        return ciudadanoRepository.save(existente);
    }

    @Override
    public void delete(Long id) {
        ciudadanoRepository.deleteById(id);
    }

   
    @Override
    public List<Ciudadano> getHabilitados() {
        return ciudadanoRepository.findByHabilitadoTrue();
    }

    @Override
    public Ciudadano habilitar(Long id) {
        Ciudadano c = getById(id);
        c.setHabilitado(true);
        return ciudadanoRepository.save(c);
    }

    @Override
    public Ciudadano inhabilitar(Long id) {
        Ciudadano c = getById(id);
        c.setHabilitado(false);
        return ciudadanoRepository.save(c);
    }

  
    @Override
    public Ciudadano verificarPorDni(String dni) {
        Ciudadano c = ciudadanoRepository.findByDni(dni)
                .orElseThrow(() -> new RuntimeException("Ciudadano no encontrado con DNI: " + dni));

        if (!c.isHabilitado()) {
            throw new RuntimeException("El ciudadano no está habilitado para votar.");
        }

        if (c.isYaVoto()) {
            throw new RuntimeException("El ciudadano ya ha votado.");
        }
      
        return c;
    }

  
    @Override
    @Transactional
    public boolean consumirToken(String tokenJwtDeAcceso) { 
        
        System.out.println("PADRON (Consumo): Recibido JWT de acceso para consumir: " + tokenJwtDeAcceso); // Log

        try {
          
            String dni = jwtService.extractDni(tokenJwtDeAcceso);

          
            Ciudadano ciudadano = ciudadanoRepository.findByDni(dni)
                .orElseThrow(() -> new RuntimeException("Ciudadano no encontrado con DNI del token: " + dni));


            if (ciudadano.isYaVoto()) {
                System.err.println("PADRON (Consumo): Intento de doble voto para DNI: " + dni);
              
                return false; 
            }

            
            ciudadano.setYaVoto(true);
            ciudadano.setFechaHoraVoto(LocalDateTime.now()); 
            ciudadanoRepository.save(ciudadano);
            emailService.enviarConfirmacionDeVoto(ciudadano);
            System.out.println("PADRON (Consumo): Voto consumido exitosamente para DNI: " + dni);
            return true; 

        } catch (Exception e) {
            
            System.err.println("PADRON (Consumo): Falla al consumir token: " + e.getMessage());
            return false; 
        }
    }

   
    @Override
    @Transactional
    public String validarJwtYGenerarTokenVoto(String dni, String tokenDelEmail) {

        System.out.println("PADRON: Iniciando validación de TOKEN DE VOTO (del email) para DNI: " + dni); // Log

        try {
            
            TokenVoto tokenEncontrado = tokenVotoRepository.findByToken(tokenDelEmail)
                .orElseThrow(() -> new RuntimeException("Token de voto no encontrado, inválido o ya fue usado."));

            
            Ciudadano ciudadano = tokenEncontrado.getCiudadano();
            if (ciudadano == null) {
                tokenVotoRepository.delete(tokenEncontrado); 
                throw new RuntimeException("Error interno: Token huérfano no asociado a ningún ciudadano.");
            }

           
            if (!ciudadano.getDni().equals(dni)) {
                 System.err.println("PADRON: DNI del token (" + ciudadano.getDni() + ") no coincide con DNI proporcionado (" + dni + ")"); // Log
                 throw new RuntimeException("Este token de voto no pertenece al DNI proporcionado.");
            }
            
          
            if (ciudadano.isYaVoto()) {
                 throw new RuntimeException("El ciudadano ya emitió su voto.");
            }
            if (!ciudadano.isHabilitado()) {
                throw new RuntimeException("El ciudadano no está habilitado para votar.");
            }
            
         
            String jwtDeAcceso = jwtService.generateAuthToken(ciudadano); 
            
            
            ciudadano.setTokenVoto(null); // Desvincular
            tokenVotoRepository.delete(tokenEncontrado); 

            System.out.println("PADRON: Validación de token de email exitosa. Generado JWT de acceso para DNI: " + dni); // Log
            
          
            return jwtDeAcceso;

        } catch (Exception e) {
             System.err.println("PADRON: Excepción durante validación de token de email: " + e.getMessage()); // Log
             throw new RuntimeException("Error al validar el token de voto: " + e.getMessage(), e);
        }
    }

   
    @Override
    public boolean puedeVotar(Long id) {
        
        return false;
    }

    @Override
    public Ciudadano marcarVoto(Long id) {
        
        return null;
    }
}