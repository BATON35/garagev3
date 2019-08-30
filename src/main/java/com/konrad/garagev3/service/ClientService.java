package com.konrad.garagev3.service;

import com.konrad.garagev3.mapper.ClientMapper;
import com.konrad.garagev3.mapper.VehicleMapper;
import com.konrad.garagev3.model.dao.Client;
import com.konrad.garagev3.model.dto.ClientDto;
import com.konrad.garagev3.repository.ClientRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class ClientService {
    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;
    private final VehicleMapper vehicleMapper;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
        clientMapper = Mappers.getMapper(ClientMapper.class);
        vehicleMapper = Mappers.getMapper(VehicleMapper.class);
    }

    public ClientDto findClientByEmail(String email) {
        return clientMapper.toClientDto(clientRepository.findByEmail(email));
    }

    public Client saveClient(Client client) {
        client.setActive(1);
        return clientRepository.save(client);
    }

    public ClientDto findClientBySurnameAndName(String surname, String name) {
        return clientMapper.toClientDto(clientRepository.findBySurnameAndName(surname, name));
    }

    public List<ClientDto> findAllActiveClients() {
        List<Client> clients = clientRepository.findByActiveIs(1);
        clients.sort(Comparator.comparing(Client::getEmail));
        List clientsDTO = new ArrayList();
        for (Client client : clients) {
            clientsDTO.add(clientMapper.toClientDto(client));
        }
        return clientsDTO;
    }

    public ClientDto deactivateClient(Long id) {
        return clientRepository.findById(id).map(client -> {
            client.setActive(0);
            return clientMapper.toClientDto(clientRepository.save(client));
        }).orElseThrow(() -> new EntityNotFoundException("Client with " + id + " doesn't exist"));
//        client.setActive(0);
//        return clientMapper.toClientDto(clientRepository.save(client));
    }

    @Transactional
    public void deleteClient(String mail) {
        clientRepository.deleteByEmail(mail);

        // clientRepository.delete(clientRepository.findByEmail(mail));
    }

    @Transactional
    public ClientDto editClient(ClientDto clientDto) {
        return clientRepository.findById(clientDto.getId()).map(client -> {
            if (!client.getEmail().equals(clientDto.getEmail())) {
                client.setEmail(clientDto.getEmail());
            }
            client.setActive(1);
            return clientMapper.toClientDto(clientRepository.save(client));
        }).orElseThrow(() -> new EntityNotFoundException("Client with " + clientDto.getId() + " doesn't exist"));
    }

    public ClientDto findById(Long id) {
        return clientMapper.toClientDto(
                clientRepository.findById(id)
                        .orElseThrow(() -> new EntityNotFoundException("Client with " + id + " doesn't exist")));
    }

    public Page<Client> findAll(@PageableDefault Pageable pageable) {
        Page<Client> clients = clientRepository.findAll(pageable);
        Page<Client> pageClient = new PageImpl<>(clients.getContent(), clients.getPageable(), clients.getContent().size());
        return pageClient;
    }

    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
    }
}
