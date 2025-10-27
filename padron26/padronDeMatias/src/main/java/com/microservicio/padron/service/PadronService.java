package com.microservicio.padron.service;

import com.microservicio.padron.entidades.Ciudadano;
import java.util.List;

public interface PadronService {

  
    List<Ciudadano> getAll();
    Ciudadano getById(Long id);
    Ciudadano create(Ciudadano ciudadano);
    Ciudadano update(Long id, Ciudadano ciudadano);
    void delete(Long id);


    List<Ciudadano> getHabilitados();
    Ciudadano habilitar(Long id);
    Ciudadano inhabilitar(Long id);

  
    Ciudadano verificarPorDni(String dni);

    
    boolean consumirToken(String tokenString);

   
    String validarJwtYGenerarTokenVoto(String dni, String tokenJwt);

    
    boolean puedeVotar(Long id);
    Ciudadano marcarVoto(Long id);
}