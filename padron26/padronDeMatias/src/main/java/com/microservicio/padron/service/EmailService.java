package com.microservicio.padron.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.microservicio.padron.entidades.Ciudadano;
import com.microservicio.padron.entidades.Mesa; // Importar Mesa

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;

   
    @Async
    public void enviarTokenDeVoto(Ciudadano ciudadano, String tokenDeVoto) {
        
        if (ciudadano.getEmail() == null || ciudadano.getEmail().isEmpty()) {
            System.err.println("Error: El ciudadano con DNI " + ciudadano.getDni() + " no tiene email registrado. No se puede enviar token.");
            return;
        }

        try {
            SimpleMailMessage mensaje = new SimpleMailMessage();
            mensaje.setTo(ciudadano.getEmail()); 
            mensaje.setSubject("Tu Habilitación para Votar - Elecciones 2025");
            
            String cuerpo = String.format(
                "Hola %s %s,\n\n" +
                "Estás habilitado para votar.\n" +
                "Tu código de autorización (token) para emitir tu voto es el siguiente:\n\n" +
                "%s\n\n" +
                "Copiá este código y pegalo en la pantalla de votación para validar tu ingreso.\n\n" +
                "Gracias por participar.",
                
                ciudadano.getNombre(),
                ciudadano.getApellido(),
                tokenDeVoto
            );
            
            mensaje.setText(cuerpo);
            mailSender.send(mensaje);
        
        } catch (Exception e) {
            System.err.println("Error al enviar email de token a " + ciudadano.getEmail() + ": " + e.getMessage());
        }
    }


    
    @Async
    public void enviarConfirmacionDeVoto(Ciudadano ciudadano) {
        
        if (ciudadano.getEmail() == null || ciudadano.getEmail().isEmpty()) {
            System.err.println("Error: El ciudadano con DNI " + ciudadano.getDni() + " no tiene email registrado. No se puede enviar confirmación.");
            return;
        }

        try {
            SimpleMailMessage mensaje = new SimpleMailMessage();
            mensaje.setTo(ciudadano.getEmail()); 
            mensaje.setSubject("Confirmación de Voto - Elecciones 2025");
            
            String nombreMesa = "Mesa no especificada";
            String lugarVotacion = "Lugar no especificado";

            if (ciudadano.getMesa() != null) {
                Mesa mesa = ciudadano.getMesa();
                nombreMesa = (mesa.getNombre() != null) ? mesa.getNombre() : "Mesa " + mesa.getId();
                lugarVotacion = (mesa.getUbicacion() != null) ? mesa.getUbicacion() : lugarVotacion;
            }

            String cuerpo = String.format(
                "Hola %s %s,\n\n" +
                "Le confirmamos que su voto ha sido registrado exitosamente.\n\n" +
                "Datos de su voto:\n" +
                "- Número de Orden: %s\n" +  
                "- Fecha y Hora: %s\n" + 
                "- Mesa de Votación: %s\n" +  
                "- Lugar: %s\n\n" +  
                "Gracias por participar.",
                
                ciudadano.getNombre(),
                ciudadano.getApellido(),
                ciudadano.getId(), // 
                ciudadano.getFechaHoraVoto(),
                nombreMesa, 
                lugarVotacion 
            );
            
            mensaje.setText(cuerpo);
            mailSender.send(mensaje);
        
        } catch (Exception e) {
            System.err.println("Error al enviar email de confirmación a " + ciudadano.getEmail() + ": " + e.getMessage());
        }
    }
}