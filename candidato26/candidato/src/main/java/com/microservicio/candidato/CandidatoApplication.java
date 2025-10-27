package com.microservicio.candidato;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.microservicio.candidato.entidades.Candidato;
import com.microservicio.candidato.enums.Roles;
import com.microservicio.candidato.repository.CandidatoRepository;

@SpringBootApplication
public class CandidatoApplication implements CommandLineRunner {
	
	  @Autowired
	    private CandidatoRepository candidatoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CandidatoApplication.class, args);
	}

	
	@Override
    public void run(String... args) throws Exception {

      
		Candidato candidato1 = new Candidato(12345678, "Nahuel", "Barreto", "Masculino",
		        "Partido Liberal", "https://i.ibb.co/N89B8P8/Captura-de-pantalla-2025-10-27-052349.png", Roles.PRESIDENTE);
		Candidato candidato2 = new Candidato(87654321, "Leonel", "Carmen", "Masculino",
		        "Partido Liberal", "https://i.ibb.co/CspBcqd1/Gzun-YYx-XQAAk-CHA.png", Roles.VICEPRESIDENTE);
		Candidato candidato3 = new Candidato(11223344, "Matias", "Flores", "Masculino",
		        "Partido Liberal", "https://i.ibb.co/ABC456/3.png", Roles.GOBERNADOR);

		
		Candidato candidato4 = new Candidato(22334455, "Armando", "Paredes", "Masculino",
		        "Partido Neoliberal", "https://ibb.co/fzbTRDBW", Roles.PRESIDENTE);
		Candidato candidato5 = new Candidato(99887766, "Juan", "Perez", "Masculino",
		        "Partido Neoliberal", "https://i.ibb.co/GHI012/5.png", Roles.VICEPRESIDENTE);
		Candidato candidato6 = new Candidato(33445566, "Lautaro", "Martinez", "Femenino",
		        "Partido Neoliberal", "https://i.ibb.co/JKL345/6.png", Roles.GOBERNADOR);

		// Partido Popular
		Candidato candidato7 = new Candidato(44556677, "Joaco", "Turro", "Masculino",
		        "Partido Negro", "https://i.ibb.co/NdZRQkDn/joaco-turro-1.jpg", Roles.PRESIDENTE);
		Candidato candidato8 = new Candidato(55667788, "Camila", "Caballo", "Femenino",
		        "Partido Negro", "https://i.ibb.co/PQR901/8.png", Roles.VICEPRESIDENTE);
		Candidato candidato9 = new Candidato(66778899, "Valentin", "Fierro", "Masculino",
		        "Partido Negro", "https://i.ibb.co/STU234/9.png", Roles.GOBERNADOR);

       
        candidatoRepository.save(candidato1);
        candidatoRepository.save(candidato2);
        candidatoRepository.save(candidato3);
        candidatoRepository.save(candidato4);
        candidatoRepository.save(candidato5);
        candidatoRepository.save(candidato6);
        candidatoRepository.save(candidato7);
        candidatoRepository.save(candidato8);
        candidatoRepository.save(candidato9);
        
    }
	
	
}
