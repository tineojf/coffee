package com.cavoshcoffee.backend.mapper;

import com.cavoshcoffee.backend.dto.response.UsuarioResponseDTO;
import com.cavoshcoffee.backend.entity.Usuario;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {
    public static UsuarioResponseDTO toDTO(Usuario usuario) {
        if (usuario == null) {
            return null;
        }

        UsuarioResponseDTO dto = new UsuarioResponseDTO();
        dto.setId(usuario.getId());
        dto.setNombres(usuario.getNombres());
        dto.setEmail(usuario.getCorreo());
        return dto;
    }
}
