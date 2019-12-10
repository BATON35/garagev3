package com.konrad.garagev3.mapper.Impl;

import com.konrad.garagev3.mapper.CarServiceMapper;
import com.konrad.garagev3.mapper.PartMapper;
import com.konrad.garagev3.mapper.ServicePartMapper;
import com.konrad.garagev3.mapper.ServicePartResponseMapper;
import com.konrad.garagev3.model.dao.ServicePart;
import com.konrad.garagev3.model.dto.ServicePartResponseDto;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class ServicePartResponseMapperImpl implements ServicePartResponseMapper {
    private final ServicePartMapper servicePartMapper;
    private final CarServiceMapper carServiceMapper;
    private final PartMapper partMapper;

    public ServicePartResponseMapperImpl() {
        this.servicePartMapper = Mappers.getMapper(ServicePartMapper.class);
        this.carServiceMapper = Mappers.getMapper(CarServiceMapper.class);
        this.partMapper = Mappers.getMapper(PartMapper.class);
    }

    @Override
    public ServicePartResponseDto toServicePartResponse(ServicePart servicePart) {
        return ServicePartResponseDto.builder()
                .carServiceDto(carServiceMapper.toCarServiceDto(servicePart.getCarService()))
                .createdDate(servicePart.getCreatedDate())
                .partDto(servicePart.getParts()
                        .stream()
                        .map(partMapper::toPartDto)
                        .collect(Collectors.toList()))
                .build();

    }
}
