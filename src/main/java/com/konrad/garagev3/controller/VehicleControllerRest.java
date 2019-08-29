package com.konrad.garagev3.controller;

import com.konrad.garagev3.mapper.VehicleDtoMapper;
import com.konrad.garagev3.model.dto.VehicleDto;
import com.konrad.garagev3.service.VehicleService;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/vehicles")
public class VehicleControllerRest {
    private final VehicleService vehicleService;
    private final VehicleDtoMapper vehicleMapper;

    @Autowired
    public VehicleControllerRest(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
        this.vehicleMapper = Mappers.getMapper(VehicleDtoMapper.class);
    }

    @GetMapping("/{id}")
    public VehicleDto getById(@PathVariable Long id) {
        return vehicleMapper.vehicleToVehicleDto(vehicleService.findById(id));
    }

    @GetMapping
    public Page<VehicleDto> getList(Pageable pageable) {
        return vehicleService.findAll(pageable).map(vehicleMapper::vehicleToVehicleDto);
    }

    @PostMapping
    public VehicleDto save(@RequestBody VehicleDto vehicleDto) {
        return vehicleMapper.vehicleToVehicleDto(vehicleService.saveVehicle(
                vehicleMapper.vehicleDtoToVehicle(vehicleDto)));
    }

    @PutMapping
    public VehicleDto update(@RequestBody VehicleDto vehicleDto) {
        return vehicleMapper.vehicleToVehicleDto(vehicleService.saveVehicle(
                vehicleMapper.vehicleDtoToVehicle(vehicleDto)));
    }

    @DeleteMapping("/{id}")
    public void delete(Long id) {
        vehicleService.deleteVehicle(id);
    }
}
