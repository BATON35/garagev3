package com.konrad.garagev3.mapper;

import com.konrad.garagev3.model.dao.Job;
import com.konrad.garagev3.model.dto.JobDto;
import org.mapstruct.Mapper;

@Mapper
public interface JobMapper {
    Job toJob(JobDto jobDto);

    JobDto toJobDto(Job job);
}
