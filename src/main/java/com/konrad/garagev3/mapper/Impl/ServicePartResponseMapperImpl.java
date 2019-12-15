package com.konrad.garagev3.mapper.Impl;

import com.konrad.garagev3.mapper.CarServiceMapper;
import com.konrad.garagev3.mapper.PartMapper;
import com.konrad.garagev3.mapper.JobMapper;
import com.konrad.garagev3.mapper.ServicePartResponseMapper;
import com.konrad.garagev3.model.dao.Job;
import com.konrad.garagev3.model.dto.ServicePartResponseDto;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class ServicePartResponseMapperImpl implements ServicePartResponseMapper {
    private final JobMapper jobMapper;
    private final CarServiceMapper carServiceMapper;
    private final PartMapper partMapper;

    public ServicePartResponseMapperImpl() {
        this.jobMapper = Mappers.getMapper(JobMapper.class);
        this.carServiceMapper = Mappers.getMapper(CarServiceMapper.class);
        this.partMapper = Mappers.getMapper(PartMapper.class);
    }

    @Override
    public ServicePartResponseDto toServicePartResponse(Job job) {
        return ServicePartResponseDto.builder()
                .carServiceDto(carServiceMapper.toCarServiceDto(job.getCarService()))
                .createdDate(job.getCreatedDate())
                .partsDto(job.getParts()
                        .stream()
                        .map(partMapper::toPartDto)
                        .collect(Collectors.toList()))
                .build();

    }
}
