package com.prueba.mappers;

import com.prueba.entity.Cuenta;
import com.prueba.model.CuentaDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ClienteMapper.class})
public interface CuentaMapper {

    Cuenta toEntity(CuentaDto dto);

    CuentaDto toDto(Cuenta entity);

    List<Cuenta> toEntity(List<CuentaDto> dto);

    List<CuentaDto> toDto(List<Cuenta> entity);
}
