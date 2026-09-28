package com.citasvet.citasvet.model;

import com.citasvet.citasvet.model.enums.Especie;
import com.citasvet.citasvet.model.enums.Sexo;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "mascotas")
public class Mascota {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Especie especie;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Sexo sexo;

    private String raza;
    private LocalDate fechaNacimiento;
    @Column(nullable = false, updatable = false)
    private LocalDate fechaRegistro;
    @Column(nullable = false)
    private Boolean activa;
    @PrePersist
    void prePersist(){
        fechaRegistro = LocalDate.now();
        activa = true;
    }

}
