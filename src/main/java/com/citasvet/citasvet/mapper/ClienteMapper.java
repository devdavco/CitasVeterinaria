package com.citasvet.citasvet.mapper;

import com.citasvet.citasvet.dto.request.ClienteActualizacionRequest;
import com.citasvet.citasvet.dto.request.ClienteRegistroRequest;
import com.citasvet.citasvet.dto.response.ClienteResponse;
import com.citasvet.citasvet.model.Cliente;
import org.springframework.stereotype.Component;

@Component
public class ClienteMapper {
    public Cliente toEntity(ClienteRegistroRequest request) {
        Cliente cliente = new Cliente();
        cliente.setDocumento(request.documento());
        cliente.setNombre(request.nombre());
        cliente.setApellido(request.apellido());
        cliente.setFechaNacimiento(request.fechaNacimiento());
        cliente.setTelefono(request.telefono());
        cliente.setEmail(request.email());
        cliente.setDireccion(request.direccion());

        return cliente;
    }

    public ClienteResponse toResponse(Cliente cliente) {
        return new ClienteResponse(cliente.getId(), cliente.getDocumento(), cliente.getNombre(), cliente.getApellido(), cliente.getFechaNacimiento(), cliente.getTelefono(), cliente.getEmail(), cliente.getDireccion(), cliente.getFechaRegistro());
    }

    public void updateEntity(ClienteActualizacionRequest request, Cliente cliente) {
        cliente.setNombre(request.nombre());
        cliente.setApellido(request.apellido());
        cliente.setFechaNacimiento(request.fechaNacimiento());
        cliente.setTelefono(request.telefono());
        cliente.setEmail(request.email());
        cliente.setDireccion(request.direccion());
    }
}
