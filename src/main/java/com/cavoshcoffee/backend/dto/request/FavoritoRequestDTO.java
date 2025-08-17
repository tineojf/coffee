package com.cavoshcoffee.backend.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// todo add Validation
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FavoritoRequestDTO {
    private Long idUsuario;
    private Long idProducto;
}
