package com.konrad.garagev3.controller;

import com.konrad.garagev3.model.Role;
import com.konrad.garagev3.model.User;
import com.konrad.garagev3.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class UserController {
    private final UserService userService;

    @Autowired
    UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/admin/addUser")
    public ModelAndView addUser(@Valid User user, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        User userExists = userService.findUserByEmail(user.getEmail());
        if (userExists != null) {
            bindingResult
                    .rejectValue("email", "error.user",
                            "Adres email: " + user.getEmail() +  "  znajduje sie już w bazie danych");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.addObject("allRoles", userService.findAllRoles());
            modelAndView.setViewName("addUser");
        } else {
            userService.SaveUserWithPrivileges(user);
            modelAndView.addObject("successMessage", "Dodano nowego urzytkownika");
            modelAndView.addObject("user", new User());
            modelAndView.addObject("allRoles", userService.findAllRoles());
            modelAndView.addObject("role", new Role());
            modelAndView.setViewName("addUser");

        }
        return modelAndView;
    }

    @RequestMapping(value = "/admin/addUser", method = RequestMethod.GET)
    public ModelAndView showAddUser() {
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user", user);
        modelAndView.addObject("allRoles", userService.findAllRoles());
        modelAndView.addObject("role", new Role());
        modelAndView.setViewName("addUser");
        return modelAndView;
    }


    @GetMapping("/admin/deleteUser")
    public ModelAndView showDeleteUser() {
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("deleteUser");
        return modelAndView;
    }
    @PostMapping("/admin/deleteUser")
    public ModelAndView deleteUser(User user, BindingResult bindingResult){
        ModelAndView modelAndView = new ModelAndView();
        User userExists = userService.findUserByEmail(user.getEmail());
        if (userExists == null) {
            bindingResult
                    .rejectValue("email", "error.user",
                            "Utzytkownik o wprowadzonym adresie email nie istnieje");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("deleteUser");
        } else {
            userService.deleteUser(user.getEmail());
            modelAndView.addObject("successMessage", "User has been deleted successfully");
            modelAndView.addObject("user", new User());
            modelAndView.setViewName("deleteUser");

        }
        return modelAndView;
    }


}
