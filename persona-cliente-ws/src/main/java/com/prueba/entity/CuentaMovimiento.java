package com.prueba.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Data
@Entity
@Table(name = "cuenta_movimiento")
public class CuentaMovimiento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cuenta")
    private Cuenta cuenta;
    @Column(name = "fecha")
    private LocalDate fecha;
    @Column(name = "tipo_movimiento", length = Integer.MAX_VALUE)
    private String tipoMovimiento;
    @Column(name = "valor", precision = 19, scale = 2)
    private BigDecimal valor;
    @Column(name = "saldo", precision = 19, scale = 2)
    private BigDecimal saldo;
    private String estado;

}