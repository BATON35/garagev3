package com.konrad.garagev3.mapper;

import com.konrad.garagev3.model.dao.Part;
import com.konrad.garagev3.model.dto.PartDto;
import org.mapstruct.Mapper;

@Mapper
public interface PartMapper {
    PartDto toPartDto(Part part);
    Part toPart(PartDto partDto);
}
