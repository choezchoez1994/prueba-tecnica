package com.prueba.model;

import com.prueba.entity.Persona;
import lombok.Data;

@Data
public class ClienteDto {
    private Long id;
    private PersonaDto persona;
    private String password;
    private String estado;
}
