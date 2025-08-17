package com.cavoshcoffee.backend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FavoritoResponseDTO {
    private Long id;
    private Long idUsuario;
    private String nombreUsuario;
    private Long idProducto;
    private String nombreProducto;
}
