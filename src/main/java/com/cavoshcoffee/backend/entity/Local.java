package com.cavoshcoffee.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Local")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Local {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "RazonSocial", nullable = false, length = 100)
    private String razonSocial;
    @Column(name = "Direccion", nullable = false, length = 150)
    private String direccion;
    @Column(name = "idDistrito", nullable = false)
    private Integer idDistrito;
    @Column(name = "Distrito", nullable = false, length = 50)
    private String distrito;
    @Column(name = "Horario", nullable = false, length = 100)
    private String horario;
    @Column(name = "Latitud", nullable = false, length = 20)
    private String latitud;
    @Column(name = "Longitud", length = 20)
    private String longitud;
}
