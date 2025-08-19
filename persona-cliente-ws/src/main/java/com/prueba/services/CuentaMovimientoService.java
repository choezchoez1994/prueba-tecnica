package com.prueba.services;

import com.prueba.entity.Cuenta;
import com.prueba.entity.CuentaMovimiento;
import com.prueba.mappers.CuentaMovimientoMapper;
import com.prueba.model.CuentaMovimientoDto;
import com.prueba.model.EstadoCuentaDto;
import com.prueba.model.RespuestaWs;
import com.prueba.repository.CuentaJpaRepository;
import com.prueba.repository.CuentaMovimientoJpaRepository;
import com.prueba.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CuentaMovimientoService {

    private static final Log log = LogFactory.getLog(CuentaMovimientoService.class);
    private final CuentaMovimientoJpaRepository cuentaMovimientoJpaRepository;
    private final CuentaMovimientoMapper cuentaMovimientoMapper;
    private final CuentaJpaRepository cuentaJpaRepository;

    public RespuestaWs guardar(CuentaMovimientoDto data) {
        RespuestaWs rw = null;
        try {
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
                        ctaMov.setSaldoInicial(ultimoMovimiento.getSaldo());
                        ctaMov.setSaldo(ultimoMovimiento.getSaldo().subtract(data.getValor()));
                        ctaMov.setValor(data.getValor().multiply(new BigDecimal(-1)));
                        break;
                }
                ctaMov = cuentaMovimientoJpaRepository.save(ctaMov);
                rw = RespuestaWs.builder()
                        .estado(true)
                        .mensaje("Movimiento guardado exitosamente")
                        .build();
            } else {
                rw = RespuestaWs.builder()
                        .estado(false)
                        .mensaje("Error: Cuenta no encontrada")
                        .build();
            }
        }catch (Exception e) {
            log.error("Error al guardar el movimiento de cuenta: ", e);
            rw = RespuestaWs.builder()
                    .estado(false)
                    .mensaje("Error al guardar el movimiento de cuenta: " + e.getMessage())
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

    public List<EstadoCuentaDto> estadoCuentaMovimientos(Long idcliente, LocalDate inicio, LocalDate fin) {
        List<CuentaMovimiento> movimientos = cuentaMovimientoJpaRepository.findByCuenta_Cliente_IdAndEstadoAndFechaBetweenOrderByIdDesc(idcliente, "ACTIVO", inicio, fin);
        List<EstadoCuentaDto> estadoCuentaDtoList = new LinkedList<>();
        if (Utils.isNotEmpty(movimientos)) {
            movimientos.stream().forEach(ctaMov -> {
                EstadoCuentaDto estadoCuentaDto = EstadoCuentaDto.builder()
                        .cliente(ctaMov.getCuenta().getCliente().getPersona().getNombre())
                        .fecha(ctaMov.getFecha())
                        .numCuenta(ctaMov.getCuenta().getNumCuenta())
                        .tipoCuenta(ctaMov.getCuenta().getTipo())
                        .saldoInicial(ctaMov.getSaldoInicial())
                        .montoMovimiento(ctaMov.getValor())
                        .saldoFinal(ctaMov.getSaldo())
                        .estado(ctaMov.getEstado())
                        .build();
                estadoCuentaDtoList.add(estadoCuentaDto);
            });
        }

        return estadoCuentaDtoList;
    }
}
