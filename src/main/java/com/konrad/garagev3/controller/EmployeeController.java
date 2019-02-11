package com.konrad.garagev3.controller;

import com.konrad.garagev3.model.Role;
import com.konrad.garagev3.model.User;
import com.konrad.garagev3.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EmployeeController {
    private final UserService userService;

    @Autowired
    EmployeeController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/employee")
    public ModelAndView employee(){
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user", user);
        modelAndView.addObject("allRoles", userService.findAllRoles());
        modelAndView.addObject("role", new Role());
        modelAndView.setViewName("employee");
        return modelAndView;
    }
}
