package com.citasvet.citasvet.service;

import com.citasvet.citasvet.model.Cliente;
import com.citasvet.citasvet.model.Mascota;
import com.citasvet.citasvet.repository.MascotaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MascotaService {
    private final MascotaRepository mascotaRepository;
    private final ClienteService clienteService;

    public MascotaService(MascotaRepository mascotaRepository, ClienteService clienteService) {
        this.mascotaRepository = mascotaRepository;
        this.clienteService = clienteService;
    }

    public Mascota registrar(Mascota mascota, Long clienteId) {

        Cliente clienteMascota = clienteService.buscarPorId(clienteId);
        mascota.setCliente(clienteMascota);
        return mascotaRepository.save(mascota);

    }
    public List<Mascota> listarMascotasCliente(Long clienteId){

        clienteService.buscarPorId(clienteId);
        return mascotaRepository.findByClienteId(clienteId);
    }

    public Mascota buscarPorId(Long id) {
        return mascotaRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("No existe la mascota con el id: " + id));
    }

    public Mascota actualizar(Long id, Mascota datos) {
        Mascota existente = buscarPorId(id);
        existente.setNombre(datos.getNombre());
        existente.setEspecie(datos.getEspecie());
        existente.setRaza(datos.getRaza());
        existente.setSexo(datos.getSexo());
        existente.setFechaNacimiento(datos.getFechaNacimiento());
        return mascotaRepository.save(existente);
    }

    public void desactivarMascota(Long id) {
        Mascota existente = buscarPorId(id);
        existente.setActiva(false);
        mascotaRepository.save(existente);
    }
    public void activarMascota(Long id) {
        Mascota existente = buscarPorId(id);
        existente.setActiva(true);
        mascotaRepository.save(existente);
    }

}
