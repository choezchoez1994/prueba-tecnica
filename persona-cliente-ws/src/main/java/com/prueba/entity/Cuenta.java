package com.prueba.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "cuenta")
public class Cuenta {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente")
    private Cliente cliente;
    @Column(name = "num_cuenta", nullable = false, length = Integer.MAX_VALUE)
    private String numCuenta;
    @Column(name = "tipo", nullable = false, length = Integer.MAX_VALUE)
    private String tipo;
    @Column(name = "saldo_inicial", nullable = false, precision = 19, scale = 2)
    private BigDecimal saldoInicial;
    @Column(name = "estado", length = Integer.MAX_VALUE)
    private String estado;

}