package com.cavoshcoffee.backend.mapper;

import com.cavoshcoffee.backend.dto.response.FavoritoResponseDTO;
import com.cavoshcoffee.backend.entity.Favorito;
import com.cavoshcoffee.backend.entity.Producto;
import com.cavoshcoffee.backend.entity.Usuario;
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

    public static Favorito toEntity(Usuario usuario, Producto producto) {
        if (usuario == null || producto == null) {
            return null;
        }

        Favorito newFavorito = new Favorito();
        newFavorito.setUsuario(usuario);
        newFavorito.setProducto(producto);
        return newFavorito;
    }
}
