package com.cavoshcoffee.backend.mapper;

import com.cavoshcoffee.backend.dto.request.LocalRequestDTO;
import com.cavoshcoffee.backend.dto.response.LocalResponseDTO;
import com.cavoshcoffee.backend.entity.Distrito;
import com.cavoshcoffee.backend.entity.Local;

public class LocalMapper {
    // RequestDTO → Entity
    public static Local toEntity(LocalRequestDTO dto, Distrito distrito) {
        Local local = new Local();
        local.setRazonSocial(dto.getRazonSocial());
        local.setDireccion(dto.getDireccion());
        local.setDistrito(distrito);
        local.setHorario(dto.getHorario());
        local.setLatitud(dto.getLatitud());
        local.setLongitud(dto.getLongitud());
        return local;
    }

    // Entity → ResponseDTO
    public static LocalResponseDTO toResponseDTO(Local local) {
        LocalResponseDTO dto = new LocalResponseDTO();
        dto.setId(local.getId());
        dto.setRazonSocial(local.getRazonSocial());
        dto.setDireccion(local.getDireccion());
        dto.setDistritoNombre(local.getDistrito() != null ? local.getDistrito().getNombre() : null);
        dto.setHorario(local.getHorario());
        dto.setLatitud(local.getLatitud());
        dto.setLongitud(local.getLongitud());
        return dto;
    }
}
