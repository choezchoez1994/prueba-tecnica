package com.prueba.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class EstadoCuentaDto {

    private LocalDate fecha;
    private String cliente;
    private String numCuenta;
    private String tipoCuenta;
    private BigDecimal saldoInicial;
    private String estado;
    private BigDecimal montoMovimiento;
    private BigDecimal saldoFinal;
}
