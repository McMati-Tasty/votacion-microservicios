package com.microservicio.padron.dto;

public class CiudadanoDTO {

    private Long id;
    private String nombre;
    private String dni;
    private boolean habilitado;
    private boolean yaVoto;

    public CiudadanoDTO() {}

    public CiudadanoDTO(Long id, String nombre, String dni, boolean habilitado, boolean yaVoto) {
        this.id = id;
        this.nombre = nombre;
        this.dni = dni;
        this.habilitado = habilitado;
        this.yaVoto = yaVoto;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDni() { return dni; }
    public void setDni(String dni) { this.dni = dni; }

    public boolean isHabilitado() { return habilitado; }
    public void setHabilitado(boolean habilitado) { this.habilitado = habilitado; }

    public boolean isYaVoto() { return yaVoto; }
    public void setYaVoto(boolean yaVoto) { this.yaVoto = yaVoto; }
}

