package com.citasvet.citasvet.repository;

import com.citasvet.citasvet.model.Mascota;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MascotaRepository extends JpaRepository<Mascota, Long> {
    List<Mascota> findByClienteId(Long idCliente);
    boolean existsByClienteId(Long idCliente);

}
