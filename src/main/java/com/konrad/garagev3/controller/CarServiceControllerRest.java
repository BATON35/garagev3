package com.konrad.garagev3.controller;

import com.konrad.garagev3.mapper.CarServiceMapper;
import com.konrad.garagev3.model.dao.CarService;
import com.konrad.garagev3.model.dto.CarServiceDto;
import com.konrad.garagev3.model.dto.ServicePartDto;
import com.konrad.garagev3.service.CarServiceService;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/car-services")
public class CarServiceControllerRest {

    private final CarServiceService carServiceService;
    private final CarServiceMapper carServiceMapper;

    @Autowired
    public CarServiceControllerRest(CarServiceService carServiceService) {
        this.carServiceService = carServiceService;
        this.carServiceMapper = Mappers.getMapper(CarServiceMapper.class);
    }

    @GetMapping
    public Page<CarServiceDto> getCarServiceList(@RequestParam Integer page, @RequestParam Integer size) {
        return carServiceService.findAll(PageRequest.of(page, size)).map(carServiceMapper::toCarServiceDto);
    }

    @GetMapping("{id}")
    public CarServiceDto getCarServiceById(@PathVariable Long id) {
        return carServiceMapper.toCarServiceDto(carServiceService.findById(id));
    }
    @GetMapping("/auto-complete")
    public List<CarServiceDto> autocompleteCarService(@RequestParam String text) {
        return carServiceService.autocompleteParts(text);
    }

    @PostMapping
    public CarServiceDto saveCarService(@RequestBody CarServiceDto carServiceDto) {
        CarService carService = carServiceService.saveCarService(carServiceMapper.toCarService(carServiceDto));
        return carServiceMapper.toCarServiceDto(carService);
    }

    @PutMapping
    public  CarServiceDto updateCarService(@RequestBody CarServiceDto carServiceDto) {
        CarService carService = carServiceService.saveCarService(carServiceMapper.toCarService(carServiceDto));
        return carServiceMapper.toCarServiceDto(carService);
    }

    @DeleteMapping("/{id}")
    public void deleteCarService(@PathVariable Long id) {
        carServiceService.deleteCarService(id);
    }

}
