package com.cavoshcoffee.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Usuario")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombres", nullable = false, length = 100)
    private String nombres;
    @Column(name = "Correo", nullable = false, length = 100, unique = true)
    private String correo;
    @Column(name = "Passwordd", nullable = false, length = 100)
    private String passwordd;
    @Column(name = "Login", nullable = false)
    private Boolean login;
}
