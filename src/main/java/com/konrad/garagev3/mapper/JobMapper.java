package com.konrad.garagev3.mapper;

import com.konrad.garagev3.model.dao.Job;
import com.konrad.garagev3.model.dto.JobDto;
import org.mapstruct.Mapper;

public interface JobMapper {
    JobDto toJobDto(Job job);
}
