package com.microservicio.padron.entidades;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "mesa")
public class Mesa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre; 
    private String ubicacion; 

    @OneToMany(mappedBy = "mesa")
    private List<Ciudadano> ciudadanos = new ArrayList<>();

    
    public Mesa() {}

    public Mesa(String nombre, String ubicacion) {
        this.nombre = nombre;
        this.ubicacion = ubicacion;
    }

  
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getUbicacion() { return ubicacion; }
    public void setUbicacion(String ubicacion) { this.ubicacion = ubicacion; }

    public List<Ciudadano> getCiudadanos() { return ciudadanos; }
    public void setCiudadanos(List<Ciudadano> ciudadanos) { this.ciudadanos = ciudadanos; }
}
