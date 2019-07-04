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

    @Autowired
    ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Client findClientByEmail(String email) {
        return clientRepository.findClientByEmail(email);
    }

    public Client saveClient(ClientDto clientDto) {
        ClientDtoMapper clientMapper = Mappers.getMapper(ClientDtoMapper.class);
        Client client = clientMapper.ClientDtoToClient(clientDto);
        return clientRepository.save(client);
    }

    public Client findClientBySurnameAndName(String surname, String name) {
        return clientRepository.findClientBySurnameAndName(surname, name);
    }
}
