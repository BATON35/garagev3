package com.konrad.garagev3.service.impl;

import com.konrad.garagev3.repository.CarRepository;
import com.konrad.garagev3.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;

    @Override
    public List<String> getBrands() {
        return carRepository.findAllBrand();
    }

    @Override
    public List<String> getModels(String brand) {
        return carRepository.findAllModelByBrand(brand);
    }

    @Override
    public List<String> getProductionDate(String brand, String model) {
        return carRepository.findProductionDateByBrandAndModel(brand, model);
    }
}
