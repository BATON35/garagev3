package com.konrad.garagev3.controller;

import com.konrad.garagev3.exeption.DuplicateEntryException;
import com.konrad.garagev3.mapper.VehicleMapper;
import com.konrad.garagev3.model.dao.Vehicle;
import com.konrad.garagev3.model.dto.VehicleDto;
import com.konrad.garagev3.service.VehicleService;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public VehicleDto getVehicleById(@PathVariable Long id) {
        return vehicleMapper.toVehicleDto(vehicleService.findById(id));
    }

    @GetMapping
    public Page<VehicleDto> getVehicleList(Pageable pageable) {
        return vehicleService.findAll(pageable).map(vehicleMapper::toVehicleDto);
    }
    @GetMapping("/autoComplete")
    public List<VehicleDto> autocompleteVehicle(@RequestParam String text) {
        return vehicleService.autocompleteWorker(text);
    }

    @PostMapping("/{clientId}")
    public VehicleDto saveVehicle(@RequestBody VehicleDto vehicleDto, @PathVariable Long clientId) throws DuplicateEntryException {
        Vehicle vehicle = vehicleService.saveVehicle(vehicleMapper.toVehicle(vehicleDto), clientId);
        return vehicleMapper.toVehicleDto(vehicle);
    }

    @PutMapping
    public VehicleDto updateVehicle(@RequestBody VehicleDto vehicleDto) throws DuplicateEntryException {
        Vehicle vehicle = vehicleService.update(vehicleMapper.toVehicle(vehicleDto));
        return vehicleMapper.toVehicleDto(vehicle);
    }

    @DeleteMapping("/{id}")
    public void deleteVehicle(@PathVariable Long id) {
        vehicleService.deleteVehicle(id);
    }

    @PatchMapping("/{id}")
    public void toggleNotification(@PathVariable Long id){
        vehicleService.toggleNotification(id);
    }

    @GetMapping("/photo/{id}")
    public List<byte[]> getPhotosPaths(@PathVariable Long id){
       return vehicleService.getPhotosPaths(id);
    }
}
