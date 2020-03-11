package com.konrad.garagev3.controller;

import com.konrad.garagev3.mapper.ClientMapper;
import com.konrad.garagev3.model.dao.Client;
import com.konrad.garagev3.model.dto.ClientDto;
import com.konrad.garagev3.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
public class ClientControllerRest {
    private final ClientService clientService;
    private final ClientMapper clientDtoMapper;

    @Autowired
    public ClientControllerRest(ClientService clientService, ClientMapper clientDtoMapper) {
        this.clientService = clientService;
        this.clientDtoMapper = clientDtoMapper;
    }

    @GetMapping("/search")
    @PreAuthorize("@securityService.hasAccessToDeletedUserList(#deleted)")
    public Page<ClientDto> searchClients(@RequestParam String searchText, @RequestParam Integer page, @RequestParam Integer size, @RequestParam(required = false) boolean deleted) {
        return clientService.searchClients(searchText, PageRequest.of(page, size), deleted);
    }

    @GetMapping("/autoComplete")
    public List<String> autocomplete(@RequestParam String text) {
        return clientService.autocompleteClients(text);
    }

    @GetMapping("/{id}")
    public ClientDto getClientById(@PathVariable Long id) {
        return clientService.findById(id);
    }

    @GetMapping("/{page}/{size}")
    @PreAuthorize("@securityService.hasAccessToDeletedUserList(#deleted)")
    public Page<ClientDto> getClientList(@PathVariable Integer page, @PathVariable Integer size, @RequestParam(required = false) Boolean deleted) {
        return clientService.findAll(PageRequest.of(page, size), deleted).map(clientDtoMapper::toClientDto);
    }

    @PostMapping
    public ClientDto saveClient(@RequestBody ClientDto clientDto) {
        Client client = clientDtoMapper.toToClient(clientDto);
        return clientDtoMapper.toClientDto(clientService.saveClient(client));
    }

    @PutMapping
    public ClientDto updateClient(@RequestBody ClientDto clientDto) {
        Client client = clientDtoMapper.toToClient(clientDto);
        return clientDtoMapper.toClientDto(clientService.saveClient(client));
    }

    @PutMapping("/restore")
    public void restoreClient(Long id) {
        clientService.restore(id);
    }

    @DeleteMapping("/{id}")
    public void deleteClient(@PathVariable Long id) {
        clientService.deleteClient(id);
    }


}
