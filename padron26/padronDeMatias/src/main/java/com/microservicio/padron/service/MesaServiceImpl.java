package com.microservicio.padron.service;

import com.microservicio.padron.entidades.Ciudadano;
import com.microservicio.padron.entidades.Mesa;
import com.microservicio.padron.repository.MesaRepository;
import com.microservicio.padron.repository.PadronRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MesaServiceImpl implements MesaService {

    private final MesaRepository mesaRepo;
    private final PadronRepository padronRepo;

    public MesaServiceImpl(MesaRepository mesaRepo, PadronRepository padronRepo) {
        this.mesaRepo = mesaRepo;
        this.padronRepo = padronRepo;
    }

    @Override
    public Mesa crearMesa(Mesa mesa) {
        return mesaRepo.save(mesa);
    }

    @Override
    public List<Mesa> listarMesas() {
        return mesaRepo.findAll();
    }

    @Override
    public Mesa asignarCiudadano(Long idMesa, Long idCiudadano) {
        Mesa mesa = mesaRepo.findById(idMesa)
                .orElseThrow(() -> new RuntimeException("Mesa no encontrada"));

        Ciudadano ciudadano = padronRepo.findById(idCiudadano)
                .orElseThrow(() -> new RuntimeException("Ciudadano no encontrado"));

        ciudadano.setMesa(mesa);
        padronRepo.save(ciudadano);

        return mesa;
    }
}
