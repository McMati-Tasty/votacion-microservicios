package com.microservicio.candidato.entidades;

import com.microservicio.candidato.enums.Roles;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "candidatos")
@Data
public class Candidato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_candidato;

    @Column(nullable = false, unique = true)
    private Integer dni;

    @Column(nullable = false)
    private String nombre_candidato;

    @Column(nullable = false)
    private String apellido_candidato;

    private String sexo_candidato;
    private String partido;
    
    
    @Enumerated (EnumType.STRING)
	private Roles rol;
    
    @Column(columnDefinition = "TEXT")
    private String imagen_candidato;

   
    public Candidato() {}

    public Integer getId_candidato() {
		return id_candidato;
	}

	public void setId_candidato(Integer id_candidato) {
		this.id_candidato = id_candidato;
	}

	public Integer getDni() {
		return dni;
	}

	public void setDni(Integer dni) {
		this.dni = dni;
	}

	public String getNombre_candidato() {
		return nombre_candidato;
	}

	public void setNombre_candidato(String nombre_candidato) {
		this.nombre_candidato = nombre_candidato;
	}

	public String getApellido_candidato() {
		return apellido_candidato;
	}

	public void setApellido_candidato(String apellido_candidato) {
		this.apellido_candidato = apellido_candidato;
	}

	public String getSexo_candidato() {
		return sexo_candidato;
	}

	public void setSexo_candidato(String sexo_candidato) {
		this.sexo_candidato = sexo_candidato;
	}

	public String getPartido() {
		return partido;
	}

	public void setPartido(String partido) {
		this.partido = partido;
	}

	public String getImagen_candidato() {
		return imagen_candidato;
	}

	public void setImagen_candidato(String imagen_candidato) {
		this.imagen_candidato = imagen_candidato;
	}

	public Candidato(Integer dni, String nombre_candidato, String apellido_candidato,
                     String sexo_candidato, String partido, String imagen_candidato, Roles rol) {
        this.dni = dni;
        this.nombre_candidato = nombre_candidato;
        this.apellido_candidato = apellido_candidato;
        this.sexo_candidato = sexo_candidato;
        this.partido = partido;
        this.rol = rol;
        this.imagen_candidato = imagen_candidato;
    }
	
	
}
