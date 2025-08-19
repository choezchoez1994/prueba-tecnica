package com.prueba.repository;

import com.prueba.entity.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for Cuenta entity.
 */
public interface CuentaJpaRepository extends JpaRepository<Cuenta, Long> {
}
