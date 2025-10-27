package com.microservicio.usuarios.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.microservicio.usuarios.Entidades.OpcionAdmin;
import com.microservicio.usuarios.repository.OpcionAdminRepository;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class OpcionAdminServiceImp implements OpcionAdminService {

    private final OpcionAdminRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<OpcionAdmin> listarOpciones() throws Exception {
        try {
            return repository.findAll();
        } catch (Exception e) {
            throw new Exception("Error al listar opciones del administrador", e);
        }
    }
}
