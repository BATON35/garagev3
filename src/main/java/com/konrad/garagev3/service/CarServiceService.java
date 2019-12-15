package com.konrad.garagev3.service;

import com.konrad.garagev3.mapper.CarServiceMapper;
import com.konrad.garagev3.model.dao.CarService;
import com.konrad.garagev3.model.dto.CarServiceDto;
import com.konrad.garagev3.repository.CarServiceRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarServiceService {

    private static CarServiceRepository carServiceRepository;
    private static CarServiceMapper carServiceMapper;

    @Autowired
    public CarServiceService(CarServiceRepository carServiceRepository) {
        CarServiceService.carServiceRepository = carServiceRepository;
        carServiceMapper = Mappers.getMapper(CarServiceMapper.class);
    }


    public Page<CarService> findAll(Pageable pageable) {
        Page<CarService> carServices = carServiceRepository.findAll(pageable);
        Page<CarService> pageCarServices = new PageImpl<>(carServices.getContent(), carServices.getPageable(), carServices.getTotalElements());
        return pageCarServices;

    }

    public CarService findById(Long id) {
        return carServiceRepository.findById(id).orElseThrow(()->new EntityNotFoundException());
    }

    public CarService saveCarService(CarService carService) {
        return carServiceRepository.save(carService);
    }

    public void deleteCarService(Long id) {
        carServiceRepository.deleteById(id);
    }

    public List<CarServiceDto> autocompleteParts(String searchText) {
        return carServiceRepository.findByNameContaining(searchText)
                .stream()
                .map(carServiceMapper::toCarServiceDto)
                .collect(Collectors.toList());
    }
}
