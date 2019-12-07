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
public class UserControllerRest {

    private final UserService userService;
    private final UserMapper userMapper;


    @Autowired
    public UserControllerRest(UserService userService) {
        this.userService = userService;
        userMapper = Mappers.getMapper(UserMapper.class);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/search")
    public Page<UserDto> searchUsers(@RequestParam String searchText, @RequestParam Integer page, @RequestParam Integer size) {
        return userService.searchUsers(searchText, PageRequest.of(page, size));
    }
    @GetMapping("/info")
    public UserDto userInfo() {
        return userMapper.toUserDto(userService.getInfo());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable Long id) {
        return userMapper.toUserDto(userService.findById(id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{page}/{size}/{hasRole}")
    public Page<UserDto> getUserList(@PathVariable Integer page, @PathVariable Integer size, @PathVariable Boolean hasRole) {
        return userService.findAll(PageRequest.of(page, size), hasRole).map(userMapper::toUserDto);
    }

    @PostMapping
    public UserDto saveUser(@RequestBody UserDto userDto) {
        User user = userMapper.toUser(userDto);
        return userMapper.toUserDto(userService.saveUser(user));
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping
    public UserDto updateUser(@RequestBody UserDto userDto) {
        User user = userMapper.toUser(userDto);
        return userMapper.toUserDto(userService.saveUser(user));
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

}
