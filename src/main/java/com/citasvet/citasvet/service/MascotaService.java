package com.citasvet.citasvet.service;

import com.citasvet.citasvet.dto.request.MascotaActualizacionRequest;
import com.citasvet.citasvet.dto.request.MascotaRegistroRequest;
import com.citasvet.citasvet.dto.response.MascotaResponse;
import com.citasvet.citasvet.mapper.MascotaMapper;
import com.citasvet.citasvet.model.Cliente;
import com.citasvet.citasvet.model.Mascota;
import com.citasvet.citasvet.repository.ClienteRepository;
import com.citasvet.citasvet.repository.MascotaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MascotaService {
    private final MascotaRepository mascotaRepository;
    private final ClienteService clienteService;
    private final MascotaMapper mascotaMapper;
    private final ClienteRepository clienteRepository;

    public MascotaService(MascotaRepository mascotaRepository, ClienteService clienteService, MascotaMapper mascotaMapper, ClienteRepository clienteRepository) {
        this.mascotaRepository = mascotaRepository;
        this.clienteService = clienteService;
        this.mascotaMapper = mascotaMapper;
        this.clienteRepository = clienteRepository;
    }

    public MascotaResponse registrar(Long clienteId, MascotaRegistroRequest request) {

        Cliente clienteMascota = clienteService.buscarEntidadPorId(clienteId);
        Mascota mascota = mascotaMapper.toEntity(request);
        mascota.setCliente(clienteMascota);
        Mascota guardada =  mascotaRepository.save(mascota);
        return mascotaMapper.toResponse(guardada);

    }


    public List<MascotaResponse> listarMascotasCliente(Long clienteId){

        clienteService.buscarEntidadPorId(clienteId);

        return mascotaRepository.findByClienteId(clienteId)
                .stream()
                .map(mascotaMapper::toResponse)
                .toList();
    }

    public Mascota buscarEntidadPorId(Long id) {
        return mascotaRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("No existe la mascota con el id: " + id));
    }
    public MascotaResponse buscarPorId(Long id) {
        return mascotaMapper.toResponse(buscarEntidadPorId(id));
    }

    public MascotaResponse actualizar(Long id, MascotaActualizacionRequest request) {
        Mascota existente = buscarEntidadPorId(id);
        mascotaMapper.updateEntity(request, existente);
        Mascota mascota = mascotaRepository.save(existente);
        return mascotaMapper.toResponse(mascota);
    }

    public MascotaResponse desactivarMascota(Long id) {
        Mascota existente = buscarEntidadPorId(id);
        existente.setActiva(false);
        Mascota mascota = mascotaRepository.save(existente);
        return mascotaMapper.toResponse(mascota);
    }
    public MascotaResponse activarMascota(Long id) {
        Mascota existente = buscarEntidadPorId(id);
        existente.setActiva(true);
        Mascota mascota = mascotaRepository.save(existente);
        return mascotaMapper.toResponse(mascota);
    }

}
