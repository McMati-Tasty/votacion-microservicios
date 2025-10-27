package com.microservicio.votacion.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query; 
import org.springframework.stereotype.Repository;
import com.microservicio.votacion.entidades.Voto;

@Repository
public interface VotoRepository extends JpaRepository<Voto, Integer> { 

    public interface ConteoVotos {
        Integer getCandidatoId();
        Long getTotalVotos();
    }

    
    @Query("SELECT v.idPresidente as candidatoId, COUNT(v) as totalVotos " +
           "FROM Voto v GROUP BY v.idPresidente")
    List<ConteoVotos> contarPorPresidente();

    
    @Query("SELECT v.idVicepresidente as candidatoId, COUNT(v) as totalVotos " +
           "FROM Voto v GROUP BY v.idVicepresidente")
    List<ConteoVotos> contarPorVicepresidente();

   
    @Query("SELECT v.idGobernador as candidatoId, COUNT(v) as totalVotos " +
           "FROM Voto v GROUP BY v.idGobernador")
    List<ConteoVotos> contarPorGobernador();
    
  
}