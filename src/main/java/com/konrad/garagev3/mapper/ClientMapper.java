package com.konrad.garagev3.mapper;

import com.konrad.garagev3.model.dao.Client;
import com.konrad.garagev3.model.dto.ClientDto;

public interface ClientMapper {
    ClientDto toClientDto(Client client);

    Client toToClient(ClientDto clientDto);
}
