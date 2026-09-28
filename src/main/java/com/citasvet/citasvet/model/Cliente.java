package com.citasvet.citasvet.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "clientes")
@Getter
@Setter
@NoArgsConstructor
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String documento;

    @Column(nullable = false)
    private String nombre;
    @Column(nullable = false)
    private String apellido;
    @Column(nullable = false, updatable = false)
    private LocalDate fechaRegistro;
    private LocalDate fechaNacimiento;
    @Column(nullable = false)
    private String telefono;
    private String email;
    private String direccion;

    @PrePersist
    void prePersist(){
        fechaRegistro = LocalDate.now();
    }
}
