package com.prueba.services;

import com.prueba.entity.Cliente;
import com.prueba.mappers.ClienteMapper;
import com.prueba.mappers.PersonaMapper;
import com.prueba.model.ClienteDto;
import com.prueba.model.PersonaDto;
import com.prueba.model.RespuestaWs;
import com.prueba.repository.ClienteJpaRepository;
import com.prueba.repository.PersonaJpaRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ClienteService {


    private static final Log log = LogFactory.getLog(ClienteService.class);

    private final ClienteJpaRepository clienteJpaRepository;
    private final ClienteMapper clienteMapper;
    private final PersonaServices personaServices;

    public RespuestaWs guardar(ClienteDto data) {
        RespuestaWs rw = null;
        PersonaDto personaDto = null;
        Cliente cliente = null;
        try {
            if (data.getPersona().getId() == null) {
                RespuestaWs respuestaWs = personaServices.buscaByIdentificacion(data.getPersona().getIdentificacion());
                if (!respuestaWs.getEstado()) {
                    respuestaWs = personaServices.guardar(data.getPersona());
                }
                if (respuestaWs.getEstado()) {
                    personaDto = (PersonaDto) respuestaWs.getData();
                    data.setPersona(personaDto);
                } else {
                    respuestaWs = RespuestaWs.builder()
                            .estado(false)
                            .mensaje("Error al guardar la persona: " + respuestaWs.getMensaje())
                            .build();
                    return respuestaWs;
                }
            }
            cliente = clienteMapper.toCliente(data);
            cliente.setEstado("ACTIVO");
            cliente = clienteJpaRepository.save(cliente);
            rw = RespuestaWs.builder()
                    .data(cliente)
                    .mensaje("Cliente guardado correctamente")
                    .estado(true)
                    .build();
        } catch (Exception e) {
            log.error("Error al guardar el cliente: ", e);
            rw = RespuestaWs.builder()
                    .estado(false)
                    .mensaje("Error al guardar el cliente: " + e.getMessage())
                    .build();
        }
        return rw;
    }

    public RespuestaWs eliminar(Long id) {
        RespuestaWs rw = null;
        try {
            Cliente cliente = clienteJpaRepository.findById(id).orElse(null);
            if (cliente != null) {
                cliente.setEstado("INACTIVO");
                clienteJpaRepository.save(cliente);
                rw = RespuestaWs.builder()
                        .estado(true)
                        .mensaje("Cliente eliminado correctamente")
                        .build();
            } else {
                rw = RespuestaWs.builder()
                        .estado(false)
                        .mensaje("Cliente no encontrado")
                        .build();
            }
        } catch (Exception e) {
            rw = RespuestaWs.builder()
                    .estado(false)
                    .mensaje("Error al eliminar el cliente: " + e.getMessage())
                    .build();
        }
        return rw;
    }
}
