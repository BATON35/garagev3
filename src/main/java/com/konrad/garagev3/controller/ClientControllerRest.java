package com.konrad.garagev3.controller;

import com.konrad.garagev3.mapper.ClientDtoMapper;
import com.konrad.garagev3.model.dao.Client;
import com.konrad.garagev3.model.dto.ClientDto;
import com.konrad.garagev3.service.ClientService;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/clients")
public class ClientControllerRest {
    private final ClientService clientService;
    private final ClientDtoMapper clientDtoMapper;

    @Autowired
    public ClientControllerRest(ClientService clientService) {
        this.clientService = clientService;
        clientDtoMapper = Mappers.getMapper(ClientDtoMapper.class);
    }


    @GetMapping("/{id}")
    public ClientDto getById(@PathVariable Long id) {
        return clientService.findById(id);
    }

    @GetMapping
    public Page<ClientDto> getList(Pageable pageable) {
        return clientService.findAll(pageable).map(clientDtoMapper::clientToClientDto);
    }

    @PostMapping
    public ClientDto save(@RequestBody ClientDto clientDto) {
        Client client = clientDtoMapper.clientDtoToClient(clientDto);
        return clientDtoMapper.clientToClientDto(clientService.saveClient(client));
    }

    @PutMapping
    public ClientDto update(@RequestBody ClientDto clientDto) {
        Client client = clientDtoMapper.clientDtoToClient(clientDto);
        return clientDtoMapper.clientToClientDto(clientService.saveClient(client));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        clientService.deleteClient(id);
    }
}
