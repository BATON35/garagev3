package com.konrad.garagev3.mapper;

import com.konrad.garagev3.model.dao.Job;
import com.konrad.garagev3.model.dto.JobResponseDto;

public interface JobResponseMapper {
    JobResponseDto toServicePartResponse(Job job);
}