package com.citasvet.citasvet.dto.request;

import java.time.LocalDate;

public record ClienteActualizacionRequest(String nombre, String apellido, LocalDate fechaNacimiento, String telefono, String email, String direccion) {
}
