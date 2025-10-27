package com.microservicio.padron.controller;

import com.microservicio.padron.entidades.Mesa;
import com.microservicio.padron.service.MesaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mesas")
public class MesaController {

    private final MesaService mesaService;

    public MesaController(MesaService mesaService) {
        this.mesaService = mesaService;
    }

    @PostMapping
    public Mesa crearMesa(@RequestBody Mesa mesa) {
        return mesaService.crearMesa(mesa);
    }

    @GetMapping
    public List<Mesa> listarMesas() {
        return mesaService.listarMesas();
    }

    @PostMapping("/{idMesa}/asignar/{idCiudadano}")
    public Mesa asignarCiudadano(
            @PathVariable Long idMesa,
            @PathVariable Long idCiudadano) {
        return mesaService.asignarCiudadano(idMesa, idCiudadano);
    }
}
