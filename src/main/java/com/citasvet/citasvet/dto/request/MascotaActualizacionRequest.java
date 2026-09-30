package com.citasvet.citasvet.dto.request;

import com.citasvet.citasvet.model.enums.Especie;
import com.citasvet.citasvet.model.enums.Sexo;

import java.time.LocalDate;

public record MascotaActualizacionRequest(String nombre, Especie especie, Sexo sexo, String raza, LocalDate fechaNacimiento) {
}
