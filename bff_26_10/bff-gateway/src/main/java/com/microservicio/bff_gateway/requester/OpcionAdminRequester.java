package com.microservicio.bff_gateway.requester;

import java.util.List;
import com.microservicio.bff_gateway.dto.OpcionAdminDto;
import com.microservicio.bff_gateway.dto.ResultadoCargoBffDto; 

public interface OpcionAdminRequester {

    List<OpcionAdminDto> listarOpciones(String token) throws Exception;

   
    
    
   
}