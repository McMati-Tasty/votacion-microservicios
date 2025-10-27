package com.microservicio.padron;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

import com.microservicio.padron.entidades.Ciudadano;
import com.microservicio.padron.entidades.Mesa;
import com.microservicio.padron.repository.PadronRepository;
import com.microservicio.padron.repository.MesaRepository;

import java.util.List;
import java.util.Optional;

@SpringBootApplication
@EnableAsync
public class PadronApplication implements CommandLineRunner {

    @Autowired
    private PadronRepository padronRepository;

    @Autowired
    private MesaRepository mesaRepository;

    public static void main(String[] args) {
        SpringApplication.run(PadronApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        if (padronRepository.count() == 0 && mesaRepository.count() == 0) {

            System.out.println("Creando e inicializando mesas...");
            Mesa mesa1 = new Mesa("Mesa 001", "Escuela Fatima - Aula 67");
            Mesa mesa2 = new Mesa("Mesa 002", "Escuela Cristo Obrero - Aula 66");
            Mesa mesa3 = new Mesa("Mesa 003", "Colegio Factory - Ginasio");

            mesaRepository.saveAll(List.of(mesa1, mesa2, mesa3));

            System.out.println("Creando e inicializando ciudadanos...");

            // ciudadanos existentes
            Ciudadano c1 = new Ciudadano("Luis Fernando", "Mamani Condori", "12345678", "luisfernandomamani8622@gmail.com");
            Ciudadano c2 = new Ciudadano("Matias", "Flores", "11223344", "matiasfloresgonzales3@gmail.com");
            Ciudadano c3 = new Ciudadano("Carlos", "Gómez", "11113333", "tu56152@gmail.com");

            Ciudadano c4 = new Ciudadano("Laura", "Paz", "22224444", "laura.paz@example.com");
            Ciudadano c5 = new Ciudadano("David", "Ruiz", "33335555", "david.ruiz@example.com");
            Ciudadano c6 = new Ciudadano("Sofia", "Luna", "44446666", "sofia.luna@example.com");

            // asignación de sexo y mesas 
            c1.setSexo("M");
            c1.setMesa(mesaRepository.findByNombre("Mesa 001").orElse(mesa1));

            c2.setSexo("F");
            c2.setMesa(mesaRepository.findByNombre("Mesa 002").orElse(mesa2));

            c3.setSexo("M");
            c3.setMesa(mesaRepository.findByNombre("Mesa 003").orElse(mesa3));

            c4.setSexo("F");
            c4.setMesa(mesaRepository.findByNombre("Mesa 001").orElse(mesa1));

            c5.setSexo("M");
            c5.setMesa(mesaRepository.findByNombre("Mesa 002").orElse(mesa2));

            c6.setSexo("F");
            c6.setMesa(mesaRepository.findByNombre("Mesa 003").orElse(mesa3));

            // guardamos todos los ciudadanos
            padronRepository.saveAll(List.of(c1, c2, c3, c4, c5, c6));

            System.out.println("Mesas y 6 Ciudadanos inicializados en la base de datos.");

        } else {
            System.out.println("La base de datos ya contiene datos. No se cargaron datos iniciales.");
        }
    }
}