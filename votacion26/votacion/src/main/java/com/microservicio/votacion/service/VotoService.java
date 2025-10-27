package com.microservicio.votacion.service;

import java.util.List;
import com.microservicio.votacion.dto.VotoDto; 
import com.microservicio.votacion.dto.ResultadoCargoDto; 
import com.microservicio.votacion.entidades.Voto;

public interface VotoService {
    
    
    void votar(VotoDto votoDto) throws Exception; 
    
   
    List<Voto> listarVotos();
    
    
    Voto buscarPorId(int id) throws Exception;
    
   
    
    List<ResultadoCargoDto> getResumenPresidente();
    List<ResultadoCargoDto> getResumenVicepresidente();
    List<ResultadoCargoDto> getResumenGobernador();
}