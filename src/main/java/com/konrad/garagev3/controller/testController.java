package com.konrad.garagev3.controller;

import com.konrad.garagev3.model.dao.User;
import com.konrad.garagev3.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class testController {
    @Autowired
    private UserService userService;

    public testController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "test/user/{id}" )
    public List<User> getUser(@PathVariable(value = "id") int id) {
        return userService.findAllUsers();
    }
}
