package com.microservicio.usuarios.service;

import java.util.List;
import com.microservicio.usuarios.Entidades.OpcionAdmin;

public interface OpcionAdminService {

	List<OpcionAdmin> listarOpciones() throws Exception;
	
}
