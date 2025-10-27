package com.microservicio.bff_gateway.service;

import com.microservicio.bff_gateway.dto.VotoBffRequestDto;

import java.util.List;

import com.microservicio.bff_gateway.dto.ResultadoCargoBffDto;
import com.microservicio.bff_gateway.dto.VotoBffResponseDto;

public interface VotacionService {

    VotoBffResponseDto votar(VotoBffRequestDto votoRequest);
    
    
    
    List<ResultadoCargoBffDto> getResumenPresidente();
    List<ResultadoCargoBffDto> getResumenVicepresidente();
    List<ResultadoCargoBffDto> getResumenGobernador();
    
}