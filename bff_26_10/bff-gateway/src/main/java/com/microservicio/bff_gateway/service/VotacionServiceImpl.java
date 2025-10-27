package com.microservicio.bff_gateway.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservicio.bff_gateway.dto.VotoBffRequestDto;
import com.microservicio.bff_gateway.dto.VotoBffResponseDto;
import com.microservicio.bff_gateway.requester.VotacionRequester;

import com.microservicio.bff_gateway.dto.ResultadoCargoBffDto;

@Service
public class VotacionServiceImpl implements VotacionService {

    @Autowired
    private VotacionRequester votacionRequester;
    
    

    @Override
    public VotoBffResponseDto votar(VotoBffRequestDto votoRequest) {
        
        return votacionRequester.votar(votoRequest);
    }
    
    
    @Override
    public List<ResultadoCargoBffDto> getResumenPresidente() {
       return votacionRequester.getResumenPresidente();
    }

    @Override
    public List<ResultadoCargoBffDto> getResumenVicepresidente() {
       return votacionRequester.getResumenVicepresidente();
    }

    @Override
    public List<ResultadoCargoBffDto> getResumenGobernador() {
       return votacionRequester.getResumenGobernador();
    }
    
}