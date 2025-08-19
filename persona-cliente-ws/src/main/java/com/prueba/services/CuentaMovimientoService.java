package com.prueba.services;

import com.prueba.entity.Cuenta;
import com.prueba.entity.CuentaMovimiento;
import com.prueba.mappers.CuentaMovimientoMapper;
import com.prueba.model.CuentaMovimientoDto;
import com.prueba.model.RespuestaWs;
import com.prueba.repository.CuentaJpaRepository;
import com.prueba.repository.CuentaMovimientoJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CuentaMovimientoService {

    private final CuentaMovimientoJpaRepository cuentaMovimientoJpaRepository;
    private final CuentaMovimientoMapper cuentaMovimientoMapper;
    private final CuentaJpaRepository cuentaJpaRepository;

    public RespuestaWs guardar(CuentaMovimientoDto data) {
        RespuestaWs rw = null;
        CuentaMovimiento ctaMov = null;
        CuentaMovimiento ultimoMovimiento = cuentaMovimientoJpaRepository.findFirstByCuenta_IdAndEstadoOrderByIdDesc(data.getCuenta().getId(), "ACTIVO");
        Cuenta cta = cuentaJpaRepository.findById(data.getCuenta().getId()).orElse(null);
        if (cta != null) {
            switch (data.getTipoMovimiento()) {
                case "DEPOSITO":
                    ctaMov = crearMovimiento(cta);
                    ctaMov.setTipoMovimiento("DEPOSITO");
                    ctaMov.setSaldo(ultimoMovimiento.getSaldo().add(data.getValor()));
                    ctaMov.setValor(data.getValor());
                    break;
                case "RETIRO":
                    if (ultimoMovimiento.getSaldo().compareTo(data.getValor()) < 0) {
                        rw = RespuestaWs.builder()
                                .estado(false)
                                .mensaje("Error:\n Saldo insuficiente para el retiro.\n Su saldo actual es: " + ultimoMovimiento.getSaldo())
                                .build();
                        return rw;
                    }
                    ctaMov = crearMovimiento(cta);
                    ctaMov.setTipoMovimiento("RETIRO");
                    ctaMov.setSaldo(ultimoMovimiento.getSaldo().subtract(data.getValor()));
                    ctaMov.setValor(data.getValor().multiply(new BigDecimal(-1)));
                    break;
            }
            rw = RespuestaWs.builder()
                    .estado(true)
                    .mensaje("Movimiento guardado exitosamente")
                    .data(cuentaMovimientoMapper.toDto(cuentaMovimientoJpaRepository.save(ctaMov)))
                    .build();
        } else {
            rw = RespuestaWs.builder()
                    .estado(false)
                    .mensaje("Error: Cuenta no encontrada")
                    .build();
        }
        return rw;
    }

    public List<CuentaMovimientoDto> getAllCuentaMovimientos() {
        List<CuentaMovimiento> movimientos = cuentaMovimientoJpaRepository.findAll();
        return cuentaMovimientoMapper.toDto(movimientos);
    }

    private CuentaMovimiento crearMovimiento(Cuenta cta) {
        return CuentaMovimiento.builder()
                .cuenta(cta)
                .fecha(LocalDate.now())
                .estado("ACTIVO")
                .build();
    }
}
