package com.prueba.controllers;

import com.prueba.entity.CuentaMovimiento;
import com.prueba.mappers.CuentaMovimientoMapper;
import com.prueba.model.CuentaMovimientoDto;
import com.prueba.repository.CuentaJpaRepository;
import com.prueba.services.CuentaMovimientoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@AllArgsConstructor
@RestController
@RequestMapping("/cuentaMovimiento")
public class CuentaMovimientoApi {

    private final CuentaMovimientoService cuentaMovimientoService;

    @PostMapping("/guardar")
    public ResponseEntity<?> guardar(@RequestBody CuentaMovimientoDto cuentaMovimiento) {
        return new ResponseEntity<>(cuentaMovimientoService.guardar(cuentaMovimiento), HttpStatus.OK);
    }

    @GetMapping("estadoCuenta/reporte/" )
    public ResponseEntity<?> reporteEstadoCuenta(@RequestParam Long cuentaId, @RequestParam LocalDate fechaInicio, @RequestParam LocalDate fechaFin) {
        return new ResponseEntity<>(cuentaMovimientoService.estadoCuentaMovimientos(cuentaId, fechaInicio, fechaFin), HttpStatus.OK);
    }
}
