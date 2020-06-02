package com.konrad.garagev3.mapper.Impl;

import com.konrad.garagev3.mapper.ClientMapper;
import com.konrad.garagev3.mapper.VehicleMapper;
import com.konrad.garagev3.model.dao.Client;
import com.konrad.garagev3.model.dto.ClientDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;
import java.util.stream.Collectors;


@Component
public class ClientMapperImpl implements ClientMapper {
    @Autowired
    private VehicleMapper vehicleMapper;

    @Override
    public ClientDto toClientDto(Client client) {
        return ClientDto.builder()
                .id(client.getId())
                .vehicles(client.getVehicles()!=null ? client.getVehicles().stream()
                        .map(vehicle -> vehicleMapper.toVehicleDto(vehicle)).collect(Collectors.toSet()):null)
                .phoneNumber(client.getPhoneNumber())
                .email(client.getEmail())
                .name(client.getName())
                .surname(client.getSurname())
                .build();
    }

    @Override
    public Client toToClient(ClientDto clientDto) {
        return Client.builder()
                .id(clientDto.getId())
                .vehicles(clientDto.getVehicles() != null ? clientDto.getVehicles().stream()
                        .map(vehicleDto -> vehicleMapper.toVehicle(vehicleDto)).collect(Collectors.toSet()) : null)
                .phoneNumber(clientDto.getPhoneNumber())
                .email(clientDto.getEmail())
                .name(clientDto.getName())
                .surname(clientDto.getSurname())
                .build();
    }
}
