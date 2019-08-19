package com.konrad.garagev3.mapper;

import com.konrad.garagev3.model.dao.Vehicle;
import com.konrad.garagev3.model.dto.VehicleDto;
import org.mapstruct.Mapper;

@Mapper
public interface VehicleDtoMapper {
    VehicleDto vehicleToVehicleDto(Vehicle vehicle);
    Vehicle vehicleDtoToVehicle(VehicleDto vehicleDto);
}