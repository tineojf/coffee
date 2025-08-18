package com.cavoshcoffee.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "local")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Local {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "razonSocial", nullable = false, length = 100)
    private String razonSocial;

    @Column(name = "direccion", nullable = false, length = 150)
    private String direccion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idDistrito", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Distrito distrito;

    @Column(name = "horario", nullable = false, length = 100)
    private String horario;

    @Column(name = "latitud", nullable = false, length = 20)
    private String latitud;

    @Column(name = "longitud", nullable = false, length = 20)
    private String longitud;
}
