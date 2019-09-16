package com.konrad.garagev3.controller;

import com.konrad.garagev3.mapper.VehicleMapper;
import com.konrad.garagev3.model.dao.Vehicle;
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
    private final VehicleMapper vehicleMapper;

    @Autowired
    public VehicleControllerRest(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
        this.vehicleMapper = Mappers.getMapper(VehicleMapper.class);
    }

    @GetMapping("/{id}")
    public VehicleDto getById(@PathVariable Long id) {
        return vehicleMapper.toVehicleDto(vehicleService.findById(id));
    }

    @GetMapping
    public Page<VehicleDto> getList(Pageable pageable) {
        return vehicleService.findAll(pageable).map(vehicleMapper::toVehicleDto);
    }

    @PostMapping("/{clientId}")
    public VehicleDto save(@RequestBody VehicleDto vehicleDto, @PathVariable Long clientId) {
        Vehicle vehicle = vehicleService.saveVehicle(vehicleMapper.toToVehicle(vehicleDto), clientId);
        return vehicleMapper.toVehicleDto(vehicle);
    }

    @PutMapping
    public VehicleDto update(@RequestBody VehicleDto vehicleDto) {
        Vehicle vehicle = vehicleService.update(vehicleMapper.toToVehicle(vehicleDto));
        return vehicleMapper.toVehicleDto(vehicle);
    }

    @DeleteMapping("/{id}")
    public void delete(Long id) {
        vehicleService.deleteVehicle(id);
    }
}
