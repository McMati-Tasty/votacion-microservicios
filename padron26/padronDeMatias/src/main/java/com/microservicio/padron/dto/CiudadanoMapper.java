package com.microservicio.padron.dto;

import org.springframework.stereotype.Component;
import com.microservicio.padron.entidades.Ciudadano;

@Component
public class CiudadanoMapper {

    
    public CiudadanoDTO toDTO(Ciudadano entity) {
        if (entity == null) return null;

        return new CiudadanoDTO(
                entity.getId(),
                entity.getNombre(),
                entity.getDni(),
                entity.isHabilitado(),
                entity.isYaVoto()
        );
    }
    
    
    
    
    public DatosCiudadanoDto toDatosCiudadanoDto(Ciudadano entity) {
        if (entity == null) return null;

        DatosCiudadanoDto dto = new DatosCiudadanoDto();
        dto.setNombre(entity.getNombre());
        dto.setApellido(entity.getApellido());
        dto.setDni(entity.getDni());
        dto.setEmail(entity.getEmail());
        dto.setNombreMesa(entity.getMesa() != null ? entity.getMesa().getNombre() : null);
        return dto;
    }

    


    public Ciudadano toEntity(CiudadanoRegistroDTO dto) {
        if (dto == null) return null;

        Ciudadano c = new Ciudadano();
        c.setNombre(dto.getNombre());
        c.setDni(dto.getDni());
        c.setHabilitado(dto.isHabilitado());
        c.setYaVoto(dto.isYaVoto());
        return c;
    }

  
    public Ciudadano toEntity(CiudadanoDTO dto) {
        if (dto == null) return null;

        Ciudadano c = new Ciudadano();
        c.setId(dto.getId());
        c.setNombre(dto.getNombre());
        c.setDni(dto.getDni());
        c.setHabilitado(dto.isHabilitado());
        c.setYaVoto(dto.isYaVoto());
        return c;
    }
}

