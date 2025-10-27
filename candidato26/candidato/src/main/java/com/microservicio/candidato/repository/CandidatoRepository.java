package com.microservicio.candidato.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservicio.candidato.entidades.Candidato;

@Repository
public interface CandidatoRepository extends JpaRepository<Candidato, Integer> {
    Optional<Candidato> findByDni(Integer dni);
}
