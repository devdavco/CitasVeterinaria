package com.citasvet.citasvet.mapper;

import com.citasvet.citasvet.dto.request.MascotaActualizacionRequest;
import com.citasvet.citasvet.dto.request.MascotaRegistroRequest;
import com.citasvet.citasvet.dto.response.MascotaResponse;
import com.citasvet.citasvet.model.Mascota;
import org.springframework.stereotype.Component;

@Component
public class MascotaMapper {
    public Mascota toEntity(MascotaRegistroRequest  request) {
        Mascota mascota = new Mascota();
        mascota.setNombre(request.nombre());
        mascota.setEspecie(request.especie());
        mascota.setSexo(request.sexo());
        mascota.setRaza(request.raza());
        mascota.setFechaNacimiento(request.fechaNacimiento());

        return mascota;

    }
    public MascotaResponse toResponse(Mascota mascota) {
        return new MascotaResponse(mascota.getId(),mascota.getCliente().getId(), mascota.getNombre(),mascota.getEspecie(),mascota.getSexo(),mascota.getRaza(),mascota.getFechaNacimiento(),mascota.getFechaRegistro(),mascota.getActiva());
    }

    public void updateEntity(MascotaActualizacionRequest request, Mascota mascota){
        mascota.setNombre(request.nombre());
        mascota.setEspecie(request.especie());
        mascota.setSexo(request.sexo());
        mascota.setRaza(request.raza());
        mascota.setFechaNacimiento(request.fechaNacimiento());
    }

}
