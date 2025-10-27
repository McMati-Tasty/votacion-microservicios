package com.microservicio.padron.service;

import com.microservicio.padron.entidades.Mesa;
import java.util.List;

public interface MesaService {

    Mesa crearMesa(Mesa mesa);

    List<Mesa> listarMesas();

    Mesa asignarCiudadano(Long idMesa, Long idCiudadano);
}
