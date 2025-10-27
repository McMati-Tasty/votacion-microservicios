package com.microservicio.padron.dto;


public class LoginResponseDto {

  
    private String tokenDeVoto;

 
     
    private DatosCiudadanoDto datosCiudadano;

  

    public String getTokenDeVoto() {
        return tokenDeVoto;
    }

    public void setTokenDeVoto(String tokenDeVoto) {
        this.tokenDeVoto = tokenDeVoto;
    }

    public DatosCiudadanoDto getDatosCiudadano() {
        return datosCiudadano;
    }

    public void setDatosCiudadano(DatosCiudadanoDto datosCiudadano) {
        this.datosCiudadano = datosCiudadano;
    }
}