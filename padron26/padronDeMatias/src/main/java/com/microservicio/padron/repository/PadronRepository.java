package com.microservicio.padron.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.microservicio.padron.entidades.Ciudadano;
import java.util.List;
import java.util.Optional;

@Repository
public interface PadronRepository extends JpaRepository<Ciudadano, Long> {
    List<Ciudadano> findByHabilitadoTrue();
    Optional<Ciudadano> findByDni(String dni);
}
