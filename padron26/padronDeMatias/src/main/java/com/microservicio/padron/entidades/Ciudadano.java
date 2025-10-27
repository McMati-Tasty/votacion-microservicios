

package com.microservicio.padron.entidades;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "ciudadano")
public class Ciudadano {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String apellido;
    private String sexo;
    
    @Column(unique = true, nullable = false)
    private String dni;

    @Column(unique = true)
    private String email;

    @Lob
    private byte[] foto;

    private boolean habilitado = true;
    private boolean yaVoto = false;
    private LocalDateTime fechaHoraVoto;
    
    @ManyToOne
    @JoinColumn(name = "mesa_id")
    private Mesa mesa;

    
  
     
    @OneToOne(mappedBy = "ciudadano", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private TokenVoto tokenVoto;
  
    public Mesa getMesa() { return mesa; }
    public void setMesa(Mesa mesa) { this.mesa = mesa; }

    public Ciudadano() {}

    public Ciudadano(String nombre, String apellido, String dni, String email) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.email = email;
    }

    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }

    public String getSexo() { return sexo; }
    public void setSexo(String sexo) { this.sexo = sexo; }

    public String getDni() { return dni; }
    public void setDni(String dni) { this.dni = dni; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public byte[] getFoto() { return foto; }
    public void setFoto(byte[] foto) { this.foto = foto; }

    public boolean isHabilitado() { return habilitado; }
    public void setHabilitado(boolean habilitado) { this.habilitado = habilitado; }

    public boolean isYaVoto() { return yaVoto; }
    public void setYaVoto(boolean yaVoto) { this.yaVoto = yaVoto; }

    public LocalDateTime getFechaHoraVoto() { return fechaHoraVoto; }
    public void setFechaHoraVoto(LocalDateTime fechaHoraVoto) { this.fechaHoraVoto = fechaHoraVoto; }

    
    public TokenVoto getTokenVoto() {
        return tokenVoto;
    }

    public void setTokenVoto(TokenVoto tokenVoto) {
        this.tokenVoto = tokenVoto;
    }
    
}

