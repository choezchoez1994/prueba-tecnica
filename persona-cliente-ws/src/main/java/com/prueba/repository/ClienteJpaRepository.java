package com.prueba.repository;

import com.prueba.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * ClienteJpaRepository is a Spring Data JPA repository interface for managing Cliente entities.
 */
public interface ClienteJpaRepository extends JpaRepository<Cliente, Long> {
}
