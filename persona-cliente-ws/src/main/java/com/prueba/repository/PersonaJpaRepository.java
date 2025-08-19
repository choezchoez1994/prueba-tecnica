package com.prueba.repository;

import com.prueba.entity.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for managing Persona entities.
 */
public interface PersonaJpaRepository extends JpaRepository<Persona, Long> {

}
