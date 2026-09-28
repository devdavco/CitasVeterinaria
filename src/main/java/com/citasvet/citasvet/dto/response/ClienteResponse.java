package com.citasvet.citasvet.dto.response;

import java.time.LocalDate;

public record ClienteResponse(Long id, String documento, String nombre, String apellido, LocalDate fechaNacimiento, String telefono, String email, String direccion,LocalDate fechaRegistro) {
}
