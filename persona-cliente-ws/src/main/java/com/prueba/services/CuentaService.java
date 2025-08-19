package com.prueba.services;

import com.prueba.entity.Cuenta;
import com.prueba.entity.CuentaMovimiento;
import com.prueba.mappers.CuentaMapper;
import com.prueba.model.CuentaDto;
import com.prueba.model.CuentaMovimientoDto;
import com.prueba.model.RespuestaWs;
import com.prueba.repository.ClienteJpaRepository;
import com.prueba.repository.CuentaJpaRepository;
import com.prueba.repository.CuentaMovimientoJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@RequiredArgsConstructor
@Service
public class CuentaService {
    private final CuentaJpaRepository cuentaJpaRepository;
    private final CuentaMapper cuentaMapper;
    private final CuentaMovimientoJpaRepository cuentaMovimientoJpaRepository;

    public RespuestaWs guardar(CuentaDto data) {
        RespuestaWs rw = null;
        Cuenta cta = cuentaMapper.toEntity(data);
        cta.setEstado("ACTIVO");
        cta = cuentaJpaRepository.save(cta);
        // creamos un movimiento inicial por una cuenta nueva
        if (data.getId() == null) {
            crearMovimientoInicial(cta);
        }
        rw = RespuestaWs.builder()
                .mensaje("Cuenta guardada correctamente")
                .estado(true)
                .data(cta)
                .build();
        return rw;
    }

    public RespuestaWs eliminar(Long idCuenta) {
        RespuestaWs rw = null;
        Cuenta cta = cuentaJpaRepository.findById(idCuenta).orElse(null);
        cta.setEstado("INACTIVO");
        cta = cuentaJpaRepository.save(cta);
        rw = RespuestaWs.builder()
                .mensaje("Cuenta eliminada correctamente")
                .estado(true)
                .data(cta)
                .build();
        return rw;
    }

    private void crearMovimientoInicial(Cuenta cta) {
        CuentaMovimiento cm = CuentaMovimiento.builder()
                .cuenta(cta)
                .fecha(LocalDate.now())
                .saldo(cta.getSaldoInicial())
                .valor(cta.getSaldoInicial())
                .saldoInicial(BigDecimal.ZERO)
                .tipoMovimiento("DEPOSITO")
                .estado("ACTIVO")
                .build();
        cuentaMovimientoJpaRepository.save(cm);
    }
}
