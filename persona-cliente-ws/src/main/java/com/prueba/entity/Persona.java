package com.prueba.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "persona")
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "identificacion", length = Integer.MAX_VALUE)
    private String identificacion;
    @Column(name = "nombre", length = Integer.MAX_VALUE)
    private String nombre;
    @Column(name = "genero", length = Integer.MAX_VALUE)
    private String genero;
    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;
    @Column(name = "direccion", length = Integer.MAX_VALUE)
    private String direccion;
    @Column(name = "telefono", length = Integer.MAX_VALUE)
    private String telefono;
    @Column(name = "estado", length = Integer.MAX_VALUE)
    private String estado;

}