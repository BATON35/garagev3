package com.konrad.garagev3.mapper;

import com.konrad.garagev3.model.dao.CarService;
import com.konrad.garagev3.model.dto.CarServiceDto;
import org.mapstruct.Mapper;

@Mapper
public interface CarServiceMapper {
    CarService toCarService(CarServiceDto carServiceDto);

    CarServiceDto toCarServiceDto(CarService carService);

}
