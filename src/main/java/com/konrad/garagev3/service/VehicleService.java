package com.konrad.garagev3.service;

import com.konrad.garagev3.mapper.VehicleDtoMapper;
import com.konrad.garagev3.model.dao.Client;
import com.konrad.garagev3.model.dao.Vehicle;
import com.konrad.garagev3.model.dto.VehicleDto;

import com.konrad.garagev3.repository.ClientRepository;
import com.konrad.garagev3.repository.VehicleRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VehicleService {
    private final VehicleRepository vehicleRepository;
    private final ClientRepository clientRepository;
    private final VehicleDtoMapper vehicleMapper;

    @Autowired
    VehicleService(VehicleRepository vehicleRepository, ClientRepository clientRepository) {
        this.vehicleRepository = vehicleRepository;
        this.clientRepository = clientRepository;
        this.vehicleMapper = Mappers.getMapper(VehicleDtoMapper.class);
    }

    public VehicleDto findVehicleByNumberPlate(String numberPlate) {
        return vehicleMapper.vehicleToVehicleDto(vehicleRepository.findByNumberPlate(numberPlate));
    }

    public Vehicle saveVehicle(VehicleDto vehicleDto, String mail) {
        //todo doczytac SecurityContextHolder
        // String clientName = SecurityContextHolder.getContext().getAuthentication().getName();
        //vehicleDto.setClient(clientRepository.findByEmail(clientName));
        vehicleDto.setClient(clientRepository.findByEmail(mail));
        return vehicleRepository.save(vehicleMapper.vehicleDtoToVehicle(vehicleDto));
    }

    public List<VehicleDto> findVehicleByClientMail(String email) {
        Client client = clientRepository.findByEmail(email);
        List<Vehicle> vehicles = vehicleRepository.findByClientId(client.getId());
        ArrayList vehiclesDto = new ArrayList();
        for (Vehicle vehicle : vehicles) {
            vehiclesDto.add(vehicleMapper.vehicleToVehicleDto(vehicle));
        }
        return vehicles
                .stream()
                .map(vehicleMapper::vehicleToVehicleDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteByClientId(long id) {
        vehicleRepository.deleteByClientId(id);
    }
}
