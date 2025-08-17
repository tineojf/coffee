package com.cavoshcoffee.backend.mapper;

import com.cavoshcoffee.backend.dto.UsuarioDTO;
import com.cavoshcoffee.backend.entity.Usuario;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {
    public static UsuarioDTO toDTO(Usuario usuario) {
        if (usuario == null) {
            return null;
        }

        UsuarioDTO dto = new UsuarioDTO();
        dto.setId(usuario.getId());
        dto.setNombres(usuario.getNombres());
        dto.setEmail(usuario.getCorreo());
        return dto;
    }
}
