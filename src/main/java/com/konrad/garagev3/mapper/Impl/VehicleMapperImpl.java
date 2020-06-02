package com.konrad.garagev3.mapper.Impl;

import com.konrad.garagev3.mapper.VehicleMapper;
import com.konrad.garagev3.model.dao.Vehicle;
import com.konrad.garagev3.model.dto.VehicleDto;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class VehicleMapperImpl implements VehicleMapper {

    @Override
    public VehicleDto toVehicleDto(Vehicle vehicle) {
        return VehicleDto.builder()
                .brand(vehicle.getCar().getBrand())
                .id(vehicle.getId())
                .productionDate(vehicle.getProductionDate().toString())
                .model(vehicle.getCar().getModel())
                .notification(vehicle.isNotification())
                .numberPlate(vehicle.getNumberPlate())
                .overviewDate(vehicle.getOverviewDate())
                .hasHistory(vehicle.getJobs() != null && !vehicle.getJobs().isEmpty())
                .build();
    }

    @Override
    public Vehicle toVehicle(VehicleDto vehicleDto) {
        return Vehicle.builder()
                .id(vehicleDto.getId())
                .productionDate(LocalDate.parse(vehicleDto.getProductionDate()))
                .notification(vehicleDto.isNotification())
                .numberPlate(vehicleDto.getNumberPlate())
                .overviewDate(vehicleDto.getOverviewDate())
                .build();
    }
}
