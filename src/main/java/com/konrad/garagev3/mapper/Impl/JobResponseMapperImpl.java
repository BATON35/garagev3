package com.konrad.garagev3.mapper.Impl;

import com.konrad.garagev3.mapper.*;
import com.konrad.garagev3.model.dao.Job;
import com.konrad.garagev3.model.dto.JobResponseDto;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class JobResponseMapperImpl implements JobResponseMapper { ;
    private final CarServiceMapper carServiceMapper;
    private final PartMapper partMapper;
    private final WorkerMapper workerMapper;

    public JobResponseMapperImpl() {
        this.carServiceMapper = Mappers.getMapper(CarServiceMapper.class);
        this.partMapper = Mappers.getMapper(PartMapper.class);
        this.workerMapper = Mappers.getMapper(WorkerMapper.class);
    }

    @Override
    public JobResponseDto toServicePartResponse(Job job) {
        if ( job == null ) {
            return null;
        }
        return JobResponseDto.builder()
                .carServiceDto(carServiceMapper.toCarServiceDto(job.getCarService()))
                .createdDate(job.getCreatedDate())
                .partsDto(job.getParts()
                        .stream()
                        .map(partMapper::toPartDto)
                        .collect(Collectors.toList()))
                .workerDto(workerMapper.toWorkerDto(job.getWorker()))
                .build();

    }
}
