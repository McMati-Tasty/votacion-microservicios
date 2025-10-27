package com.microservicio.bff_gateway.dto;


public class CiudadanoLoginBffResponsePadron {

    private String tokenDeVoto;
    private DatosCiudadanoBffDto datosCiudadano;
    
    
    private String mensajeError;


	public String getTokenDeVoto() {
		return tokenDeVoto;
	}


	public void setTokenDeVoto(String tokenDeVoto) {
		this.tokenDeVoto = tokenDeVoto;
	}


	public DatosCiudadanoBffDto getDatosCiudadano() {
		return datosCiudadano;
	}


	public void setDatosCiudadano(DatosCiudadanoBffDto datosCiudadano) {
		this.datosCiudadano = datosCiudadano;
	}


	public String getMensajeError() {
		return mensajeError;
	}


	public void setMensajeError(String mensajeError) {
		this.mensajeError = mensajeError;
	} 
}