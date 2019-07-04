package com.konrad.garagev3.service;

import com.konrad.garagev3.mapper.ClientDtoMapper;
import com.konrad.garagev3.model.dao.Client;
import com.konrad.garagev3.model.dto.ClientDto;
import com.konrad.garagev3.repository.ClientRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {
    private final ClientRepository clientRepository;
    private final ClientDtoMapper clientMapper;

    @Autowired
    ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
        clientMapper = Mappers.getMapper(ClientDtoMapper.class);
    }

    public ClientDto findClientByEmail(String email) {
        return clientMapper.ClientToClientDto(clientRepository.findClientByEmail(email));
    }

    public ClientDto saveClient(ClientDto clientDto) {
        ClientDtoMapper clientMapper = Mappers.getMapper(ClientDtoMapper.class);
        Client client = clientMapper.ClientDtoToClient(clientDto);
        return clientMapper.ClientToClientDto(clientRepository.save(client));
    }

    public ClientDto findClientBySurnameAndName(String surname, String name) {
        return clientMapper.ClientToClientDto(clientRepository.findClientBySurnameAndName(surname, name));
    }
}
