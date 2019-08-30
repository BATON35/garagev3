package com.konrad.garagev3.controller;

import com.konrad.garagev3.mapper.UserMapper;
import com.konrad.garagev3.model.dao.User;
import com.konrad.garagev3.model.dto.UserDto;
import com.konrad.garagev3.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserControllerRest {

    private final UserService userService;
    private final UserMapper userMapper;


    @Autowired
    public UserControllerRest(UserService userService) {
        this.userService = userService;
        userMapper = Mappers.getMapper(UserMapper.class);
    }

    @GetMapping("/{id}")
    public UserDto getById(@PathVariable Long id) {
        return userMapper.toUserDto(userService.findById(id));
    }

    @GetMapping
    public Page<UserDto> getList(Pageable pageable) {
        return userService.findAll(pageable).map(userMapper::toUserDto);
    }

    @PostMapping
    public UserDto save(@RequestBody UserDto userDto) {
        User user = userMapper.toToUser(userDto);
        return userMapper.toUserDto(userService.saveUser(user));
    }

    @PutMapping
    public UserDto update(@RequestBody UserDto userDto) {
        User user = userMapper.toToUser(userDto);
        return userMapper.toUserDto(userService.saveUser(user));
    }

    @DeleteMapping
    public void delete(Long id) {
        userService.deleteUser(id);
    }

    @PostMapping("/signin")
    @ApiOperation(value = "${UserController.signin}")
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 422, message = "Invalid username/password supplied")})
    public String login(//
                        @ApiParam("Username") @RequestParam String username, //
                        @ApiParam("Password") @RequestParam String password) {
        return userService.signin(username, password);
    }

    @PostMapping("/signup")
    @ApiOperation(value = "${UserController.signup}")
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 403, message = "Access denied"), //
            @ApiResponse(code = 422, message = "Username is already in use"), //
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    public String signup(@ApiParam("Signup User") @RequestBody UserDto userDto) {
        return userService.signup(userMapper.toToUser(userDto));
    }
}
