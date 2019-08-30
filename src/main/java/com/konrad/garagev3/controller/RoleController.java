package com.konrad.garagev3.controller;

import com.konrad.garagev3.mapper.RoleMapper;
import com.konrad.garagev3.model.dto.RoleDto;
import com.konrad.garagev3.service.RoleService;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/roles")
public class RoleController {
    public final RoleService roleService;
    public final RoleMapper roleMapper;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
        roleMapper = Mappers.getMapper(RoleMapper.class);
    }

    @GetMapping("/{id}")
    public RoleDto getById(@PathVariable Long id) {
        return roleMapper.toRoleDto(roleService.findById(id));
    }

    @GetMapping
    public List<RoleDto> getList() {
        return roleService.findAll().stream().map(roleMapper::toRoleDto).collect(Collectors.toList());
    }

}
