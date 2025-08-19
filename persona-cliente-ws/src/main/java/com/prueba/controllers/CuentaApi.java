package com.prueba.controllers;

import com.prueba.entity.Cuenta;
import com.prueba.model.CuentaDto;
import com.prueba.services.CuentaService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/cuenta")
public class CuentaApi {

    private final CuentaService cuentaService;

    @PostMapping("/crear")
    public ResponseEntity<?> crearCuenta(@RequestBody CuentaDto cuenta) {
        return new ResponseEntity<>(cuentaService.guardar(cuenta), HttpStatus.CREATED);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarCuenta(@RequestParam Long id) {
        return new ResponseEntity<>(cuentaService.eliminar(id), HttpStatus.OK);
    }

}
