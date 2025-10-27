package com.microservicio.bff_gateway.requester;

import com.microservicio.bff_gateway.dto.CiudadanoLoginBffRequestPadron;
import com.microservicio.bff_gateway.dto.CiudadanoLoginBffResponsePadron;

public interface PadronRequester {

	CiudadanoLoginBffResponsePadron loginCiudadano(CiudadanoLoginBffRequestPadron loginRequest);
    CiudadanoLoginBffResponsePadron verificarToken(CiudadanoLoginBffRequestPadron verificarRequest);
}