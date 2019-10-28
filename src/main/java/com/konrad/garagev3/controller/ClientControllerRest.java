package com.konrad.garagev3.controller;

import com.konrad.garagev3.mapper.ClientMapper;
import com.konrad.garagev3.model.dao.Client;
import com.konrad.garagev3.model.dto.ClientDto;
import com.konrad.garagev3.model.dto.UserDto;
import com.konrad.garagev3.service.ClientService;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
public class ClientControllerRest {
    private final ClientService clientService;
    private final ClientMapper clientDtoMapper;

    @Autowired
    public ClientControllerRest(ClientService clientService) {
        this.clientService = clientService;
        clientDtoMapper = Mappers.getMapper(ClientMapper.class);
    }

    @GetMapping("/search")
    public Page<ClientDto> searchClients(@RequestParam String searchText, @RequestParam Integer page, @RequestParam Integer size) {
        return clientService.searchClients(searchText, PageRequest.of(page, size));
    }

    @GetMapping("/autoComplete")
    public List<String> autocomplete(@RequestParam String text) {
        return clientService.autocompleteClients(text);
    }

    @GetMapping("/{id}")
    public ClientDto getById(@PathVariable Long id) {
        return clientService.findById(id);
    }

    @GetMapping("/{page}/{size}")
    public Page<ClientDto> getList(@PathVariable Integer page, @PathVariable Integer size) {
        return clientService.findAll(PageRequest.of(page, size)).map(clientDtoMapper::toClientDto);
    }

    @PostMapping
    public ClientDto save(@RequestBody ClientDto clientDto) {
        Client client = clientDtoMapper.toToClient(clientDto);
        return clientDtoMapper.toClientDto(clientService.saveClient(client));
    }

    @PutMapping
    public ClientDto update(@RequestBody ClientDto clientDto) {
        Client client = clientDtoMapper.toToClient(clientDto);
        return clientDtoMapper.toClientDto(clientService.saveClient(client));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        clientService.deleteClient(id);
    }


}
