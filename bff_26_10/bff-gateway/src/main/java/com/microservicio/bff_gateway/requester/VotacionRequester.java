package com.microservicio.bff_gateway.requester;

import com.microservicio.bff_gateway.dto.VotoBffRequestDto;
import com.microservicio.bff_gateway.dto.VotoBffResponseDto;

import java.util.List;

import com.microservicio.bff_gateway.dto.ResultadoCargoBffDto;

public interface VotacionRequester {
    
    VotoBffResponseDto votar(VotoBffRequestDto votoRequest);
    
    List<ResultadoCargoBffDto> getResumenPresidente();
    List<ResultadoCargoBffDto> getResumenVicepresidente();
    List<ResultadoCargoBffDto> getResumenGobernador();

}