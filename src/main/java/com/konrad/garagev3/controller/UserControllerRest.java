package com.konrad.garagev3.controller;

import com.konrad.garagev3.mapper.UserMapper;
import com.konrad.garagev3.model.dao.User;
import com.konrad.garagev3.model.dto.UserDto;
import com.konrad.garagev3.service.UserService;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
//@CrossOrigin(origins = "http://localhost:4200", maxAge = 10_000)
public class UserControllerRest {

    private final UserService userService;
    private final UserMapper userMapper;


    @Autowired
    public UserControllerRest(UserService userService) {
        this.userService = userService;
        userMapper = Mappers.getMapper(UserMapper.class);
    }

    @GetMapping("/info")
    public UserDto userInfo() {
        return userMapper.toUserDto(userService.getinfo());
    }

    @GetMapping("/{id}")

    public UserDto getById(@PathVariable Long id) {
        return userMapper.toUserDto(userService.findById(id));
    }

    @GetMapping("/{page}/{size}")
    public Page<UserDto> getList(@PathVariable Integer page, @PathVariable Integer size) {
        return userService.findAll(PageRequest.of(page, size)).map(userMapper::toUserDto);
    }

    @PostMapping
    public UserDto save(@RequestBody UserDto userDto) {
        User user = userMapper.toToUser(userDto);
        return userMapper.toUserDto(userService.saveUser(user));
    }

    @PutMapping
    @PreAuthorize("isAuthenticated()")
    public UserDto update(@RequestBody UserDto userDto) {
        User user = userMapper.toToUser(userDto);

        return userMapper.toUserDto(userService.saveUser(user));
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(@PathVariable Long id) {
        userService.deleteUser(id);
    }

}
