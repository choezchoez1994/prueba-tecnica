package com.prueba.mappers;

import com.prueba.entity.Persona;
import com.prueba.model.PersonaDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PersonaMapper {

    Persona toEntity(PersonaDto dto);
    PersonaDto toDto(Persona entity);
    List<Persona> toEntity(List<PersonaDto> dto);
    List<PersonaDto> toDto(List<Persona> entity);
}
