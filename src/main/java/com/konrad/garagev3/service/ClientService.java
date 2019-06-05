package com.konrad.garagev3.service;

import com.konrad.garagev3.model.dao.Client;
import com.konrad.garagev3.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {
    private final ClientRepository clientRepository;

    @Autowired
    ClientService(ClientRepository ownerRepository) {
        this.clientRepository = ownerRepository;
    }

    public Client findClientByEmail(String email) {
        return clientRepository.findClientByEmail(email);
    }

    public Client saveClient(Client owner) {
        return clientRepository.save(owner);
    }

    public Client findClientBySurnameAndName(String surname, String name) {
        return clientRepository.findClientBySurnameAndName(surname, name);
    }
}
