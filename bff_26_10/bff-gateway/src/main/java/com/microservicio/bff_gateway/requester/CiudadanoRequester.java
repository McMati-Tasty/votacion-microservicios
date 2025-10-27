package com.microservicio.bff_gateway.requester;

import com.microservicio.bff_gateway.dto.DatosCiudadanoBffDto;
import java.util.List;

public interface CiudadanoRequester {
    List<DatosCiudadanoBffDto> getAll();
}