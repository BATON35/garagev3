package com.konrad.garagev3.controller;

import com.konrad.garagev3.exeption.DuplicateEntryException;
import com.konrad.garagev3.mapper.Impl.VehicleMapperImpl;
import com.konrad.garagev3.model.dao.Vehicle;
import com.konrad.garagev3.model.dto.VehicleDto;
import com.konrad.garagev3.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehicles")
public class VehicleControllerRest {
    private final VehicleService vehicleService;
    private final VehicleMapperImpl vehicleMapperImp;

    @Autowired
    public VehicleControllerRest(VehicleService vehicleService, VehicleMapperImpl vehicleMapperImp) {
        this.vehicleService = vehicleService;
        this.vehicleMapperImp = vehicleMapperImp;
    }

    @GetMapping("/{id}")
    public VehicleDto getVehicleById(@PathVariable Long id) {
        return vehicleMapperImp.toVehicleDto(vehicleService.findById(id));
    }

    @GetMapping
    public Page<VehicleDto> getVehicleList(Pageable pageable) {
        return vehicleService.findAll(pageable).map(vehicleMapperImp::toVehicleDto);
    }
    @GetMapping("/autoComplete")
    public List<VehicleDto> autocompleteVehicle(@RequestParam String text) {
        return vehicleService.autocompleteWorker(text);
    }

    @PostMapping("/{clientId}")
    public VehicleDto saveVehicle(@RequestBody VehicleDto vehicleDto, @PathVariable Long clientId) throws DuplicateEntryException {
        Vehicle vehicle = vehicleService.saveVehicle(vehicleMapperImp.toVehicle(vehicleDto), clientId);
        return vehicleMapperImp.toVehicleDto(vehicle);
    }

    @PutMapping
    public VehicleDto updateVehicle(@RequestBody VehicleDto vehicleDto) throws DuplicateEntryException {
        Vehicle vehicle = vehicleService.update(vehicleMapperImp.toVehicle(vehicleDto));
        return vehicleMapperImp.toVehicleDto(vehicle);
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
