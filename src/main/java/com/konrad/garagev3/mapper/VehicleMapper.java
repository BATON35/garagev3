package com.konrad.garagev3.mapper;

import com.konrad.garagev3.model.dao.Vehicle;
import com.konrad.garagev3.model.dto.VehicleDto;
import org.mapstruct.Mapper;

@Mapper
public interface VehicleMapper {
    VehicleDto toVehicleDto(Vehicle vehicle);

    Vehicle toVehicle(VehicleDto vehicleDto);
}