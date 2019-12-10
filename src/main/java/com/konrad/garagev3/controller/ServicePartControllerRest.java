package com.konrad.garagev3.controller;

import com.konrad.garagev3.mapper.ServicePartMapper;
import com.konrad.garagev3.mapper.ServicePartResponseMapper;
import com.konrad.garagev3.model.dao.ServicePart;
import com.konrad.garagev3.model.dto.ServicePartDto;
import com.konrad.garagev3.model.dto.ServicePartResponseDto;
import com.konrad.garagev3.service.ServicePartService;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/service-part")
public class ServicePartControllerRest {

    private final ServicePartService servicePartService;
    private final ServicePartMapper servicePartMapper;

    public ServicePartControllerRest(ServicePartService servicePartService) {
        this.servicePartService = servicePartService;
        this.servicePartMapper = Mappers.getMapper(ServicePartMapper.class);

    }

    @GetMapping("/{id}")
    public ServicePartDto getServicePartById(@PathVariable Long id) {
        return servicePartMapper.toServicePartDto(servicePartService.findById(id));
    }

    @GetMapping
    public Page<ServicePartDto> getServicePartList(@RequestParam Integer page, @RequestParam Integer size) {
        return servicePartService.findAll(PageRequest.of(page, size)).map(servicePartMapper::toServicePartDto);
    }

    @PostMapping
    public ServicePartDto saveServicePart(@RequestBody ServicePartDto servicePartDto) {
        return servicePartMapper.toServicePartDto(
                servicePartService.saveServicePart(
                        servicePartDto.getWorkerId(),
                        servicePartDto.getPartIds(),
                        servicePartDto.getServiceId(),
                        servicePartDto.getVehicleNumberPlate()));
    }

    @PutMapping
    public ServicePartDto updateServicePart(@RequestBody ServicePartDto servicePartDto) {
        return servicePartMapper.toServicePartDto(
                servicePartService.saveServicePart(
                        servicePartDto.getWorkerId(),
                        servicePartDto.getPartIds(),
                        servicePartDto.getServiceId(),
                        servicePartDto.getVehicleNumberPlate()));
    }

    @DeleteMapping("{id}")
    public void deleteServicePart(@PathVariable Long id) {
        servicePartService.deleteServicePart(id);
    }

    @GetMapping("/history")
    public List<ServicePartResponseDto> getServicePartHistory(Long vehicleId) {
        return servicePartService.getHistory(vehicleId);
    }

}
