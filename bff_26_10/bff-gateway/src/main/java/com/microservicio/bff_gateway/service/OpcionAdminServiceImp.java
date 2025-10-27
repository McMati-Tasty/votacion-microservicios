package com.microservicio.bff_gateway.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.microservicio.bff_gateway.dto.OpcionAdminDto;
import com.microservicio.bff_gateway.dto.ResultadoCargoBffDto; 
import com.microservicio.bff_gateway.requester.OpcionAdminRequester;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class OpcionAdminServiceImp implements OpcionAdminService {

    private final OpcionAdminRequester requester;

    @Value("${microservicio.candidato.url}")
    private String candidatoUrl;

    @Value("${microservicios.url.padron}")
    private String padronUrl;

    @Value("${microservicios.url.votacion}")
    private String votacionUrl;

    @Override
    public List<OpcionAdminDto> listarOpciones(String token) throws Exception {
        List<OpcionAdminDto> opciones = requester.listarOpciones(token);

        for (OpcionAdminDto opcion : opciones) {
            String titulo = opcion.getTitulo().toLowerCase();

            if (titulo.contains("votos")) {
                opcion.setEnlaceUrl("/votacion/resumen"); 
            } else if (titulo.contains("candidatos")) {
                opcion.setEnlaceUrl("/candidato/listado");
            } else if (titulo.contains("ciudadanos")) {
                opcion.setEnlaceUrl("/ciudadano/listado");
            } else if (titulo.contains("padrones")) {
                opcion.setEnlaceUrl("/padron/listado");
            }
        }

        return opciones;
    }
    
    
   
}