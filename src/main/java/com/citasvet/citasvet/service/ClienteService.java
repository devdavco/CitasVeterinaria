package com.citasvet.citasvet.service;

import com.citasvet.citasvet.dto.request.ClienteActualizacionRequest;
import com.citasvet.citasvet.dto.request.ClienteRegistroRequest;
import com.citasvet.citasvet.dto.response.ClienteResponse;
import com.citasvet.citasvet.mapper.ClienteMapper;
import com.citasvet.citasvet.model.Cliente;
import com.citasvet.citasvet.repository.ClienteRepository;
import com.citasvet.citasvet.repository.MascotaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {
    private final ClienteRepository clienteRepository;
    private final MascotaRepository mascotaRepository;
    private final ClienteMapper clienteMapper;

    public ClienteService(ClienteRepository clienteRepository, MascotaRepository mascotaRepository, ClienteMapper clienteMapper) {
        this.clienteRepository = clienteRepository;
        this.mascotaRepository = mascotaRepository;
        this.clienteMapper = clienteMapper;
    }

    public ClienteResponse registrar(ClienteRegistroRequest request) {
        if (clienteRepository.existsByDocumento(request.documento())) {
            throw new IllegalArgumentException("Ya existe un cliente con el documento " + request.documento());
        }
        Cliente cliente = clienteMapper.toEntity(request);
        Cliente guardado = clienteRepository.save(cliente);
        return clienteMapper.toResponse(guardado);
    }

    public List<ClienteResponse> listarTodos() {
        return clienteRepository.findAll()
                .stream()
                .map(clienteMapper::toResponse)
                .toList();
    }

    public ClienteResponse buscarPorId(Long id) {
        return clienteMapper.toResponse(buscarEntidadPorId(id));
    }

    public Cliente buscarEntidadPorId(Long id) {
        return clienteRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("No existe el cliente con el id: " + id));
    }

    public ClienteResponse actualizar(Long id, ClienteActualizacionRequest request) {
        Cliente cliente = buscarEntidadPorId(id);
        clienteMapper.updateEntity(request, cliente);
        Cliente guardado = clienteRepository.save(cliente);
        return clienteMapper.toResponse(guardado);
    }

    public void eliminar(Long id) {
        Cliente cliente = buscarEntidadPorId(id);
        if (mascotaRepository.existsByClienteId(id)) {
            throw new IllegalStateException("No se puede eliminar cliente con mascotas");
        }
        clienteRepository.delete(cliente);
    }

}
