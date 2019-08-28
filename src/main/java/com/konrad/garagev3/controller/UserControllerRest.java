package com.konrad.garagev3.controller;

import com.konrad.garagev3.mapper.UserDtoMapper;
import com.konrad.garagev3.model.dao.User;
import com.konrad.garagev3.model.dto.UserDto;
import com.konrad.garagev3.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserControllerRest {

    @Autowired
    private UserService userService;


    @GetMapping("/{id}")
    public UserDto getById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @GetMapping
    public Page<UserDto> getList(Pageable pageable) {
        return userService.findAll(pageable);
    }

    @PostMapping
    public User save(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @PutMapping
    public User update(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @DeleteMapping
    public void delete(Long id) {
        userService.deleteUser(id);
    }
}
