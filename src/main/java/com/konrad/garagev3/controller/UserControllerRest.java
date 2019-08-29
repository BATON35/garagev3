package com.konrad.garagev3.controller;

import com.konrad.garagev3.mapper.UserDtoMapper;
import com.konrad.garagev3.mapper.VehicleDtoMapper;
import com.konrad.garagev3.model.dao.User;
import com.konrad.garagev3.model.dto.UserDto;
import com.konrad.garagev3.service.UserService;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/users")
public class UserControllerRest {

    private final UserService userService;
    private final UserDtoMapper userMapper;


    @Autowired
    public UserControllerRest(UserService userService) {
        this.userService = userService;
        userMapper = Mappers.getMapper(UserDtoMapper.class);
    }

    @GetMapping("/{id}")
    public UserDto getById(@PathVariable Long id) {
        return userMapper.userToUserDto(userService.findById(id));
    }

    @GetMapping
    public Page<UserDto> getList(Pageable pageable) {
        return userService.findAll(pageable).map(userMapper::userToUserDto);
    }

    @PostMapping
    public UserDto save(@RequestBody UserDto userDto) {
        User user = userMapper.userDtoToUser(userDto);
        return userMapper.userToUserDto(userService.saveUser(user));
    }

    @PutMapping
    public UserDto update(@RequestBody UserDto userDto) {
        User user = userMapper.userDtoToUser(userDto);
        return userMapper.userToUserDto(userService.saveUser(user));
    }

    @DeleteMapping
    public void delete(Long id) {
        userService.deleteUser(id);
    }
}
