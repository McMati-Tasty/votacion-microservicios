package com.microservicio.votacion.entidades;

import java.time.LocalDateTime;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "votos")
public class Voto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    

    @Column(name = "id_presidente") 
    private Integer idPresidente;  

    @Column(name = "id_vicepresidente") 
    private Integer idVicepresidente;   

    @Column(name = "id_gobernador") 
    private Integer idGobernador;   

    @Column(name = "fecha_hora", nullable = false)
    private LocalDateTime fechaHora = LocalDateTime.now();

    
    public Voto(int idPresidente, int idVicepresidente, int idGobernador) {
        this.idPresidente = idPresidente;
        this.idVicepresidente = idVicepresidente;
        this.idGobernador = idGobernador;
        this.fechaHora = LocalDateTime.now();
    }
}