package com.microservicio.bff_gateway.service;

import java.util.List;
import com.microservicio.bff_gateway.dto.OpcionAdminDto;
import com.microservicio.bff_gateway.dto.ResultadoCargoBffDto; 
public interface OpcionAdminService {

    List<OpcionAdminDto> listarOpciones(String token) throws Exception;

 
  
    
}