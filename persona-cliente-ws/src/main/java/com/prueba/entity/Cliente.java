package com.prueba.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "cliente")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "persona", nullable = false)
    private Persona persona;
    @Column(name = "password", length = Integer.MAX_VALUE)
    private String password;
    @Column(name = "estado", length = Integer.MAX_VALUE)
    private String estado;

}