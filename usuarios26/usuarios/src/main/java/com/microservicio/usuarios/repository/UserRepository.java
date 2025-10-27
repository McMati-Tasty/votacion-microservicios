package com.microservicio.usuarios.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservicio.usuarios.Entidades.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	
	Optional<User> findByusername(String username);
	
	
}
