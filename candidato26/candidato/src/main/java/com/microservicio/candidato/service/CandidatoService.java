package com.microservicio.candidato.service;

import java.util.List;
import java.util.Optional;

import com.microservicio.candidato.entidades.Candidato;
import com.microservicio.candidato.dto.CandidatoDTO;

public interface CandidatoService {
    Candidato agregar(CandidatoDTO dto) throws Exception;
    List<Candidato> listado() throws Exception;
    Candidato buscarPorId(int id) throws Exception;
    void eliminar(int id) throws Exception;

   
    List<Candidato> listarImagenes() throws Exception;
    
    
    Optional<Candidato> buscarPorIdOptional(int id);


    
}
