package com.microservicio.bff_gateway.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservicio.bff_gateway.dto.CiudadanoLoginBffRequestPadron;
import com.microservicio.bff_gateway.dto.CiudadanoLoginBffResponsePadron;
import com.microservicio.bff_gateway.requester.PadronRequester;

@Service
public class PadronServiceImpl implements PadronService {

    @Autowired
    private PadronRequester padronRequester;

    @Override
    public CiudadanoLoginBffResponsePadron loginCiudadano(CiudadanoLoginBffRequestPadron loginRequest) {
        return padronRequester.loginCiudadano(loginRequest);
    }

  
    @Override
    public CiudadanoLoginBffResponsePadron verificarToken(CiudadanoLoginBffRequestPadron verificarRequest) {
        
        return padronRequester.verificarToken(verificarRequest);
    }
}