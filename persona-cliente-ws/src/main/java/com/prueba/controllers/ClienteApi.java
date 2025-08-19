package com.prueba.controllers;

import com.prueba.entity.Cliente;
import com.prueba.model.ClienteDto;
import com.prueba.services.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/cliente")
public class ClienteApi {
    private final ClienteService clienteService;

    @PostMapping("/guardar")
    public ResponseEntity<?> guardar(@RequestBody ClienteDto cliente) {
        return new ResponseEntity<>(clienteService.guardar(cliente), HttpStatus.CREATED);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        return new ResponseEntity<>(clienteService.eliminar(id), HttpStatus.NO_CONTENT);
    }
}
