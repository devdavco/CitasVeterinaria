package com.citasvet.citasvet.dto.request;

import java.time.LocalDate;

public record ClienteRegistroRequest(String documento, String nombre, String apellido, LocalDate fechaNacimiento, String telefono, String email, String direccion) {
}
