package com.konrad.garagev3.controller;

import com.konrad.garagev3.model.dao.Role;
import com.konrad.garagev3.model.dao.User;
import com.konrad.garagev3.model.dto.UserDto;
import com.konrad.garagev3.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class UserController {
    private final UserService userService;

    public UserController(@Autowired UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/addUser")
    public ModelAndView addUser() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("userDto", new UserDto());
        modelAndView.addObject("allRoles", userService.findAllRoles());
        modelAndView.addObject("role", new Role());
        modelAndView.setViewName("addUser");
        return modelAndView;
    }

    @GetMapping("/deleteUser")
    public ModelAndView showDeleteUser() {
        ModelAndView modelAndView = new ModelAndView();
        UserDto userDto = new UserDto();
        modelAndView.addObject("userDto", userDto);
        modelAndView.setViewName("deleteUser");
        return modelAndView;
    }

    @GetMapping("/user")
    public String showUsers(Model model) {
        model.addAttribute("users", userService.findAllActiveUsers());
        return "showUsers";
    }

    @PostMapping("/user")
    public ModelAndView showAddUser(@Valid UserDto user, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        UserDto userExists = userService.findUserByEmail(user.getEmail());
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
            userService.saveUserWithPrivileges(user);
            modelAndView.addObject("successMessage", "Dodano nowego urzytkownika");
            modelAndView.addObject("user", new UserDto());
            modelAndView.addObject("allRoles", userService.findAllRoles());
            modelAndView.addObject("role", new Role());
            modelAndView.setViewName("addUser");

        }
        return modelAndView;
    }

    @PutMapping("/user/{email}/active/{state}")
    public ModelAndView deactivateUser(@PathVariable(value = "email") String email, @PathVariable(value = "state", required = false) String state) {
        userService.deactivateUser(email);
        return new ModelAndView("redirect:/user");
    }

    @DeleteMapping("/user")
    public ModelAndView deleteUser(UserDto userDto, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        UserDto userExists = userService.findUserByEmail(userDto.getEmail());
        if (userExists == null) {
            bindingResult.rejectValue("email", "error.user",
                            "Utzytkownik o wprowadzonym adresie email nie istnieje");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("deleteUser");
        } else {
            userService.deleteUser(userDto.getEmail());
            modelAndView.addObject("successMessage", "User has been deleted successfully");
            modelAndView.addObject("userDto", new UserDto());
            modelAndView.setViewName("deleteUser");

        }
        return modelAndView;
    }
}
