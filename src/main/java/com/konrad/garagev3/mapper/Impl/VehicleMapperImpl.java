package com.konrad.garagev3.mapper.Impl;

import com.konrad.garagev3.mapper.VehicleMapper;
import com.konrad.garagev3.model.dao.Vehicle;
import com.konrad.garagev3.model.dto.VehicleDto;
import org.springframework.stereotype.Component;

@Component
public class VehicleMapperImpl implements VehicleMapper {

    @Override
    public VehicleDto toVehicleDto(Vehicle vehicle) {
        return VehicleDto.builder()
                .brand(vehicle.getBrand())
                .id(vehicle.getId())
                .productionDate(vehicle.getProductionDate())
                .model(vehicle.getModel())
                .notification(vehicle.isNotification())
                .numberPlate(vehicle.getNumberPlate())
                .overviewDate(vehicle.getOverviewDate())
                .hasHistory(vehicle.getJobs() == null ? false : !vehicle.getJobs().isEmpty())
                .build();
    }

    @Override
    public Vehicle toVehicle(VehicleDto vehicleDto) {
        return Vehicle.builder()
                .brand(vehicleDto.getBrand())
                .id(vehicleDto.getId())
                .productionDate(vehicleDto.getProductionDate())
                .model(vehicleDto.getModel())
                .notification(vehicleDto.isNotification())
                .numberPlate(vehicleDto.getNumberPlate())
                .overviewDate(vehicleDto.getOverviewDate())
                .build();
    }
}
