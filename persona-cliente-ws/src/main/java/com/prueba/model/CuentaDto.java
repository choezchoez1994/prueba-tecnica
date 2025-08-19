package com.prueba.model;

import com.prueba.entity.Cliente;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CuentaDto {
    private Long id;
    private Cliente cliente;
    private String numCuenta;
    private String tipo;
    private BigDecimal saldoInicial;
    private String estado;
}
