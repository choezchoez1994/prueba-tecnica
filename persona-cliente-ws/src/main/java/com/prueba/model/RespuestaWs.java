package com.prueba.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class RespuestaWs implements Serializable {

    private Boolean estado;
    private String mensaje;
    private Object data;

}
