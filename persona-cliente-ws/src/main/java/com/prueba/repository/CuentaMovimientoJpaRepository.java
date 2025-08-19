package com.prueba.repository;

import com.prueba.entity.CuentaMovimiento;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for CuentaMovimiento entity.
 */
public interface CuentaMovimientoJpaRepository extends JpaRepository<CuentaMovimiento, Long> {

    CuentaMovimiento findFirstByCuenta_IdAndEstadoOrderByIdDesc(Long cuenta_id, String estado);
}
