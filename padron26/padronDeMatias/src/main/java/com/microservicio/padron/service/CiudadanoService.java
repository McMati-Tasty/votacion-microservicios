package com.microservicio.padron.service;

import com.microservicio.padron.dto.CiudadanoLoginRequest;
import com.microservicio.padron.dto.LoginResponseDto; 

public interface CiudadanoService {

   
    
    LoginResponseDto login(CiudadanoLoginRequest loginDTO) throws Exception;

    
}