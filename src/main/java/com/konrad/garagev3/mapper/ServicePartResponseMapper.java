package com.konrad.garagev3.mapper;

import com.konrad.garagev3.model.dao.ServicePart;
import com.konrad.garagev3.model.dto.ServicePartResponseDto;
import org.mapstruct.Mapper;

public interface ServicePartResponseMapper {
    ServicePartResponseDto toServicePartResponse(ServicePart servicePart);
}
