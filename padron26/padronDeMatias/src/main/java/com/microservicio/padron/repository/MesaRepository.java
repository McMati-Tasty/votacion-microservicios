package com.microservicio.padron.repository;

import com.microservicio.padron.entidades.Mesa;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MesaRepository extends JpaRepository<Mesa, Long> {
	
	Optional<Mesa> findByNombre(String nombre);
}
