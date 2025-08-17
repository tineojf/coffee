package com.cavoshcoffee.backend.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// todo add Validation
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductoRequestDTO {
    private String nombre;
    private String descripcion;
    private Double precio;
    private String detalle;
    private String categoria;
    private String imagen;
}
