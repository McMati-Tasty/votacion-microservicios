package com.microservicio.candidato.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservicio.candidato.dto.CandidatoDTO;
import com.microservicio.candidato.entidades.Candidato;
import com.microservicio.candidato.repository.CandidatoRepository;

@Service
public class CandidatoServiceImp implements CandidatoService {

    @Autowired
    private CandidatoRepository candidatoRepository;

    @Override
    public Candidato agregar(CandidatoDTO dto) throws Exception {
        Optional<Candidato> existente = candidatoRepository.findByDni(dto.getDni());
        if (existente.isPresent()) {
            throw new Exception("Ya existe un candidato con ese DNI");
        }
        return candidatoRepository.save(dto.toEntity());
    }

    @Override
    public List<Candidato> listado() throws Exception {
        return candidatoRepository.findAll();
    }
    
    
    @Override
    public Optional<Candidato> buscarPorIdOptional(int id) {
        return candidatoRepository.findById(id);
    }
    

    @Override
    public Candidato buscarPorId(int id) throws Exception {
        return candidatoRepository.findById(id)
                .orElseThrow(() -> new Exception("Candidato no encontrado"));
    }

    @Override
    public void eliminar(int id) throws Exception {
        if (!candidatoRepository.existsById(id)) {
            throw new Exception("Candidato no encontrado");
        }
        candidatoRepository.deleteById(id);
    }

    
    @Override
    public List<Candidato> listarImagenes() throws Exception {
       
        return candidatoRepository.findAll()
                .stream()
                .filter(c -> c.getImagen_candidato() != null && !c.getImagen_candidato().isBlank())
                .collect(Collectors.toList());
    }
}
