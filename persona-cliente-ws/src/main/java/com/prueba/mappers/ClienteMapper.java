package com.prueba.mappers;

import com.prueba.entity.Cliente;
import com.prueba.model.ClienteDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {PersonaMapper.class})
public interface ClienteMapper {

    Cliente toCliente(ClienteDto clienteDto);

    ClienteDto toClienteDto(Cliente cliente);

    List<ClienteDto> toClienteDtos(List<Cliente> clientes);

    List<Cliente> toClientes(List<ClienteDto> clienteDtos);
}
