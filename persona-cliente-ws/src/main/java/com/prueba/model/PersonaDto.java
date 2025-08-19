package com.prueba.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PersonaDto {

    private Long id;
    private String identificacion;
    private String nombre;
    private String genero;
    private LocalDate fechaNacimiento;
    private String direccion;
    private String telefono;
    private String estado;
    private Integer edad;
}
