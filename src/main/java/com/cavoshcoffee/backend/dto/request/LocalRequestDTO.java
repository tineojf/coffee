package com.cavoshcoffee.backend.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocalRequestDTO {
    private String razonSocial;
    private String direccion;
    private Long distritoId;
    private String horario;
    private String latitud;
    private String longitud;
}
