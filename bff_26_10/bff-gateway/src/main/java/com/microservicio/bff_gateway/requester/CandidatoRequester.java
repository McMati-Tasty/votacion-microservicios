package com.microservicio.bff_gateway.requester;

import com.microservicio.bff_gateway.dto.CandidatoDTO;
import java.util.List;

public interface CandidatoRequester {
    CandidatoDTO agregar(CandidatoDTO dto);
    List<CandidatoDTO> listado();
    CandidatoDTO buscarPorId(int id);
    void eliminar(int id);
    List<CandidatoDTO> votables();
}
