package com.konrad.garagev3.mapper.Impl;

import com.konrad.garagev3.mapper.JobMapper;
import com.konrad.garagev3.model.dao.Job;
import com.konrad.garagev3.model.dto.JobDto;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class JobMapperImpl implements JobMapper {

    @Override
    public JobDto toJobDto(Job job) {
        if (job == null) {
            return null;
        }
        return JobDto.builder()
                .id(job.getId())
                .ServiceId(job.getCarService().getId())
                .vehicleNumberPlate(job.getVehicle().getNumberPlate())
                .workerId(job.getWorker().getId())
                .partIds(job.getParts().stream().map(e->e.getId()).collect(Collectors.toList()))
                .build();
    }
}
