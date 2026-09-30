package com.citasvet.citasvet.dto.response;

import com.citasvet.citasvet.model.enums.Especie;
import com.citasvet.citasvet.model.enums.Sexo;

import java.time.LocalDate;

public record MascotaResponse (Long id, Long clienteId, String nombre, Especie especie, Sexo sexo, String raza, LocalDate fechaNacimiento, LocalDate fechaRegistro, Boolean activa) {
}
