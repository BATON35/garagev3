package com.konrad.garagev3.controller;

import com.konrad.garagev3.service.CarService;
import com.konrad.garagev3.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/car")
@RequiredArgsConstructor
public class CarControllerRest {
    private final CarService carService;

    @GetMapping("/brand")
    public List<String> getBrand() {
        return carService.getBrands();
    }

    @GetMapping("/model")
    public List<String> getModel(@RequestParam String model) {
        return carService.getModels(model);
    }

    @GetMapping("/production")
    public List<String> getProductionDate(@RequestParam String brand, @RequestParam String model) {
        return carService.getProductionDate(brand, model);
    }



}
