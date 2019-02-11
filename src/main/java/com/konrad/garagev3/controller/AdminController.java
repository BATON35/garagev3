package com.konrad.garagev3.controller;

import com.konrad.garagev3.model.Role;
import com.konrad.garagev3.model.User;
import com.konrad.garagev3.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminController {
    private final UserService userService;

    @Autowired
    AdminController(UserService userService) {
        this.userService = userService;
    }
    @RequestMapping("admin")
    public String admin() {
        return "admin";
    }
}
