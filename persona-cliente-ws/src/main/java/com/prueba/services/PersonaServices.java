package com.prueba.services;

import com.prueba.entity.Persona;
import com.prueba.mappers.PersonaMapper;
import com.prueba.model.PersonaDto;
import com.prueba.model.RespuestaWs;
import com.prueba.repository.PersonaJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PersonaServices {

    private final PersonaJpaRepository personaJpaRepository;
    private final PersonaMapper personaMapper;

    public RespuestaWs guardar(PersonaDto data) {
        RespuestaWs respuestaWs = null;
        Persona persona = personaMapper.toEntity(data);
        try {
            persona.setEstado("ACTIVO");
            persona = personaJpaRepository.save(persona);
            respuestaWs = RespuestaWs.builder()
                    .estado(true)
                    .mensaje("Persona guardada correctamente")
                    .data(personaMapper.toDto(persona))
                    .build();
        } catch (Exception e) {
            respuestaWs = RespuestaWs.builder()
                    .estado(false)
                    .mensaje("Error al guardar la persona: " + e.getMessage())
                    .build();
        }
        return respuestaWs;
    }

}
