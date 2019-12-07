package com.konrad.garagev3.mapper;

import com.konrad.garagev3.model.dao.ServicePart;
import com.konrad.garagev3.model.dto.ServicePartDto;
import org.mapstruct.Mapper;

@Mapper
public interface ServicePartMapper {
    ServicePart toServicePart(ServicePartDto servicePartDto);

    ServicePartDto toServicePartDto(ServicePart servicePart);
}
