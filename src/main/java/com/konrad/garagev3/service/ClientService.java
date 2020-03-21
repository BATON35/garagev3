package com.konrad.garagev3.service;

import com.konrad.garagev3.mapper.ClientMapper;
import com.konrad.garagev3.mapper.VehicleMapper;
import com.konrad.garagev3.model.dao.Client;
import com.konrad.garagev3.model.dto.ClientDto;
import com.konrad.garagev3.repository.ClientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ClientService {
    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;
    private final VehicleMapper vehicleMapper;

    @Autowired
    public ClientService(ClientRepository clientRepository, VehicleMapper vehicleMapper, ClientMapper clientMapper) {
        this.clientRepository = clientRepository;
        this.clientMapper = clientMapper;
        this.vehicleMapper = vehicleMapper;
    }

    public ClientDto findClientByEmail(String email) {
        return clientMapper.toClientDto(clientRepository.findByEmailAndDeleted(email, false));
    }

    public Client saveClient(Client client) {
        return clientRepository.save(client);
    }

    public ClientDto findClientBySurnameAndName(String surname, String name) {
        return clientMapper.toClientDto(clientRepository.findBySurnameAndNameAndDeleted(surname, name, false));
    }

    @Transactional
    public void deleteClient(String mail) {
        clientRepository.deleteByEmailAndDeleted(mail, false);

        // clientRepository.delete(clientRepository.findByEmailAndDeleted(mail));
    }

    @Transactional
    public ClientDto editClient(ClientDto clientDto) {
        return clientRepository.findByIdAndDeleted(clientDto.getId(), false).map(client -> {
            if (clientDto.getEmail() != null && !client.getEmail().equals(clientDto.getEmail())) {
                client.setEmail(clientDto.getEmail());
            }
            return clientMapper.toClientDto(clientRepository.save(client));
        }).orElseThrow(() -> new EntityNotFoundException("Client with " + clientDto.getId() + " doesn't exist"));
    }

    @Cacheable(cacheNames = "Client", key = "#id")
    public ClientDto findById(Long id) {
        log.info("Object is not in cache");
        return clientMapper.toClientDto(
                clientRepository.findByIdAndDeleted(id, false)
                        .orElseThrow(() -> new EntityNotFoundException("Client with " + id + " doesn't exist")));
    }

    public Page<Client> findAll(@PageableDefault Pageable pageable, Boolean deleted) {
        Page<Client> clients = clientRepository.findByDeleted(deleted == null ? false : deleted, pageable);
        Page<Client> pageClient = new PageImpl<>(clients.getContent(), clients.getPageable(), clients.getTotalElements());
        return pageClient;
    }

    public void deleteClient(Long id) {
        clientRepository.findById(id).ifPresent(client -> {
            client.setDeleted(true);
            clientRepository.save(client);
        });
    }

    public Page<ClientDto> searchClients(String searchText, PageRequest pageRequest, boolean deleted) {
        Page<Client> clients = clientRepository.findByNameContainsOrEmailContainsAndDeleted(searchText, searchText, deleted, pageRequest);
        return new PageImpl<>(clients.getContent()
                .stream()
                .map(clientMapper::toClientDto)
                .collect(Collectors.toList()), clients.getPageable(), clients.getTotalElements());
    }

    public List<String> autocompleteClients(String searchText) {
        List<String> byAutoCompleteEmail = clientRepository.findByAutoCompleteEmail(searchText, false);
        List<String> byAutoCompleteName = clientRepository.findByAutoCompleteName(searchText, false);
        byAutoCompleteEmail.addAll(byAutoCompleteName);
        return byAutoCompleteEmail;
    }

    public void restore(Long id) {
        clientRepository.findByIdAndDeleted(id, true).ifPresent(client -> {
            client.setDeleted(false);
            clientRepository.save(client);
        });
    }
}
