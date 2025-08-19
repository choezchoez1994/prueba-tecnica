package com.prueba.mappers;

import com.prueba.entity.CuentaMovimiento;
import com.prueba.model.CuentaMovimientoDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CuentaMapper.class})
public interface CuentaMovimientoMapper {

    CuentaMovimiento toEntity(CuentaMovimientoDto cuentaMovimiento);

    CuentaMovimientoDto toDto(CuentaMovimiento cuentaMovimiento);

    List<CuentaMovimiento> toEntity(List<CuentaMovimientoDto> cuentaMovimientoDtos);

    List<CuentaMovimientoDto> toDto(List<CuentaMovimiento> cuentaMovimientos);
}
