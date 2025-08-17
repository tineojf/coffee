package com.cavoshcoffee.backend.mapper;

import com.cavoshcoffee.backend.dto.response.FavoritoResponseDTO;
import com.cavoshcoffee.backend.entity.Favorito;
import org.springframework.stereotype.Component;

@Component
public class FavoritoMapper {
    public static FavoritoResponseDTO toResponseDTO(Favorito favorito) {
        if (favorito == null) {
            return null;
        }

        return FavoritoResponseDTO.builder()
                .id(favorito.getId())
                .idUsuario(favorito.getUsuario().getId())
                .nombreUsuario(favorito.getUsuario().getNombres())
                .idProducto(favorito.getProducto().getId())
                .nombreProducto(favorito.getProducto().getNombre())
                .build();
    }
}
