package com.konrad.garagev3.controller;

import com.konrad.garagev3.model.dao.Role;
import com.konrad.garagev3.model.dao.User;
import com.konrad.garagev3.model.dto.UserDto;
import com.konrad.garagev3.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class userController {
    private final UserService userService;

    public userController(@Autowired UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/user")
    public ModelAndView addUser() {
        ModelAndView modelAndView = new ModelAndView();
        UserDto user = new UserDto();
        modelAndView.addObject("user", user);
        modelAndView.addObject("allRoles", userService.findAllRoles());
        modelAndView.addObject("role", new Role());
        modelAndView.setViewName("addUser");
        return modelAndView;
    }

    @PostMapping("/user")
    public ModelAndView showAddUser(@Valid UserDto user, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        User userExists = userService.findUserByEmail(user.getEmail());
        if (userExists != null) {
            bindingResult
                    .rejectValue("email", "error.user",
                            "Adres email: " + user.getEmail() + "  znajduje sie już w bazie danych");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.addObject("user", new UserDto());
            modelAndView.addObject("allRoles", userService.findAllRoles());
            modelAndView.addObject("role", new Role());
            modelAndView.setViewName("addUser");
        } else {
            userService.SaveUserWithPrivileges(user);
            modelAndView.addObject("successMessage", "Dodano nowego urzytkownika");
            modelAndView.addObject("user", new UserDto());
            modelAndView.addObject("allRoles", userService.findAllRoles());
            modelAndView.addObject("role", new Role());
            modelAndView.setViewName("addUser");

        }
        return modelAndView;
    }
}
