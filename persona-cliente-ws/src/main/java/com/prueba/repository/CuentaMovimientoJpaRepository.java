package com.prueba.repository;

import com.prueba.entity.CuentaMovimiento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

/**
 * Repository interface for CuentaMovimiento entity.
 */
public interface CuentaMovimientoJpaRepository extends JpaRepository<CuentaMovimiento, Long> {

    CuentaMovimiento findFirstByCuenta_IdAndEstadoOrderByIdDesc(Long cuenta_id, String estado);

    List<CuentaMovimiento> findByCuenta_Cliente_IdAndEstadoAndFechaBetweenOrderByIdDesc(
            Long idcliente,
            String estado,
            LocalDate fechaInicio,
            LocalDate fechaFin
    );

}
