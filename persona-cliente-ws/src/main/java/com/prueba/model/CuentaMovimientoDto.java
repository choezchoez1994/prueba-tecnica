package com.prueba.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class CuentaMovimientoDto {

    private Long id;
    private CuentaDto cuenta;
    private LocalDate fecha;
    private String tipoMovimiento;
    private BigDecimal valor;
    private BigDecimal saldo;
    private String estado;
}
