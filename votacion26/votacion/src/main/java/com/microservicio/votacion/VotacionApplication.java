package com.microservicio.votacion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.microservicio.votacion")
@EnableJpaRepositories(basePackages = "com.microservicio.votacion.repository")
@EntityScan(basePackages = "com.microservicio.votacion.entidades")
public class VotacionApplication {

    public static void main(String[] args) {
        SpringApplication.run(VotacionApplication.class, args);
        System.out.println(" Microservicio Votación corriendo en http://localhost:8086/swagger-ui.html");
    }
}
