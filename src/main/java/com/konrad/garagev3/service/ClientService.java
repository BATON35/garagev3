package com.konrad.garagev3.service;

import com.konrad.garagev3.mapper.ClientDtoMapper;
import com.konrad.garagev3.mapper.VehicleDtoMapper;
import com.konrad.garagev3.model.dao.Client;
import com.konrad.garagev3.model.dto.ClientDto;
import com.konrad.garagev3.model.dto.VehicleDto;
import com.konrad.garagev3.repository.ClientRepository;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class ClientService {
    private final ClientRepository clientRepository;
    private final ClientDtoMapper clientMapper;
    private final VehicleDtoMapper vehicleDtoMapper;

    @Autowired
    ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
        clientMapper = Mappers.getMapper(ClientDtoMapper.class);
        vehicleDtoMapper = Mappers.getMapper(VehicleDtoMapper.class);
    }

    public ClientDto findClientByEmail(String email) {
        return clientMapper.ClientToClientDto(clientRepository.findClientByEmail(email));
    }

    public ClientDto saveClient(ClientDto clientDto) {
        ClientDtoMapper clientMapper = Mappers.getMapper(ClientDtoMapper.class);
        Client client = clientMapper.ClientDtoToClient(clientDto);
        client.setActive(1);
        return clientMapper.ClientToClientDto(clientRepository.save(client));
    }

    ClientDto findClientBySurnameAndName(String surname, String name) {
        return clientMapper.ClientToClientDto(clientRepository.findClientBySurnameAndName(surname, name));
    }

    public List<ClientDto> findAllActiveClients() {
        List<Client> clients = clientRepository.findAllActiveClients();
        clients.sort(Comparator.comparing(Client::getEmail));
        List clientsDTO = new ArrayList();
        for (Client client : clients) {
            clientsDTO.add(clientMapper.ClientToClientDto(client));
        }
        return clientsDTO;
    }

    public ClientDto deactivateClient(String email) {
        Client client = clientRepository.findClientByEmail(email);
        client.setActive(0);
        return clientMapper.ClientToClientDto(clientRepository.save(client));
    }

    @Transactional
    public void deleteClient(String mail) {
        clientRepository.deleteClientByEmail(mail);
    }

    // TODO: 09.08.2019 czy metoda moze zwaracac np boolean zamiast ClientDto
    public ClientDto addVehicleToClient(String email, VehicleDto vehicleDto) {
        Client client = clientRepository.findClientByEmail(email);
      //  client.addVehicle(vehicleDtoMapper.vehicleDtoToVehicle(vehicleDto));
        client.setName("new name");
        return clientMapper.ClientToClientDto(clientRepository.save(client));

    }
}
