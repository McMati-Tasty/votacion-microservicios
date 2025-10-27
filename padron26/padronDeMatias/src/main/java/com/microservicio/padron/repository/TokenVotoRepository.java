package com.microservicio.padron.repository;

import com.microservicio.padron.entidades.Ciudadano; 
import com.microservicio.padron.entidades.TokenVoto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TokenVotoRepository extends JpaRepository<TokenVoto, Long> {

    Optional<TokenVoto> findByToken(String token);

    
    Optional<TokenVoto> findByCiudadano(Ciudadano ciudadano);
}