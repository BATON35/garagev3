package com.konrad.garagev3.mapper;

import com.konrad.garagev3.model.dao.Role;
import com.konrad.garagev3.model.dto.RoleDto;
import org.mapstruct.Mapper;

@Mapper
public interface RoleMapper {
    RoleDto toRoleDto(Role role);

    Role toRole(RoleDto roleDto);
}
