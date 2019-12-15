package com.konrad.garagev3.mapper;

import com.konrad.garagev3.model.dao.Job;
import com.konrad.garagev3.model.dto.ServicePartResponseDto;

public interface ServicePartResponseMapper {
    ServicePartResponseDto toServicePartResponse(Job job);
}
