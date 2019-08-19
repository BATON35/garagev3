package com.konrad.garagev3.service;

import com.konrad.garagev3.mapper.VehicleDtoMapper;
import com.konrad.garagev3.model.dao.Vehicle;
import com.konrad.garagev3.model.dto.VehicleDto;

import com.konrad.garagev3.repository.VehicleRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VehicleService {
    private final VehicleRepository vehicleRepository;
    private final VehicleDtoMapper vehicleMapper;

    @Autowired
    VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
        this.vehicleMapper = Mappers.getMapper(VehicleDtoMapper.class);
    }

    public VehicleDto findVehicleByNumberPlate(String numberPlate) {
        return vehicleMapper.vehicleToVehicleDto(vehicleRepository.findByNumberPlate(numberPlate));
    }

    public Vehicle saveVehicle(VehicleDto vehicleDto) {
        return vehicleRepository.save(vehicleMapper.vehicleDtoToVehicle(vehicleDto));
    }
}
