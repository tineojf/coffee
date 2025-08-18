package com.cavoshcoffee.backend.dto.response;

import com.cavoshcoffee.backend.entity.Local;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocalResponseDTO {
    private Long id;
    private String razonSocial;
    private String direccion;
    private String distritoNombre;
    private String horario;
    private String latitud;
    private String longitud;

}

