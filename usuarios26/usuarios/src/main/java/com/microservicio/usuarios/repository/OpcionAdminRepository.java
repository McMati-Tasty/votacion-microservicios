package com.microservicio.usuarios.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservicio.usuarios.Entidades.OpcionAdmin;

@Repository
public interface OpcionAdminRepository extends JpaRepository<OpcionAdmin, Integer> {
	
	

}
