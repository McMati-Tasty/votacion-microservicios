package com.microservicio.bff_gateway.service;


import com.microservicio.bff_gateway.dto.CiudadanoLoginBffRequestPadron;
import com.microservicio.bff_gateway.dto.CiudadanoLoginBffResponsePadron;

public interface PadronService {
    
   
    CiudadanoLoginBffResponsePadron loginCiudadano(CiudadanoLoginBffRequestPadron loginRequest);

    
    CiudadanoLoginBffResponsePadron verificarToken(CiudadanoLoginBffRequestPadron verificarRequest);
    
    
    
}