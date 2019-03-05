package com.konrad.garagev3.controller;

import com.konrad.garagev3.model.Role;
import com.konrad.garagev3.model.User;
import com.konrad.garagev3.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;

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

    @PostMapping("/admin/addUser")
    public ModelAndView showAddUser(@Valid User user, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        User userExists = userService.findUserByEmail(user.getEmail());
        if (userExists != null) {
            bindingResult
                    .rejectValue("email", "error.user",
                            "Adres email: " + user.getEmail() + "  znajduje sie już w bazie danych");
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
    public ModelAndView addUser() {
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
    public ModelAndView deleteUser(User user, BindingResult bindingResult) {
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

    @GetMapping("/admin/showUsers")
    public String showUsers(Model model) {
        model.addAttribute("users", userService.findAllUsers());
       // model.addAttribute("userToDelete", new User());
        return "showUsers";
    }

    @PostMapping("/admin/showUsers")
    public ModelAndView showUsersAfterDelete(@Valid User user, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("users", userService.findAllUsers());
        modelAndView.setViewName("showUsers");
        return modelAndView;
    }

    @GetMapping("/admin/user/delete")
    public ModelAndView showUsers(@RequestParam("id") int userId) {
       // userService.deleteUserById(userId);
        userService.deactivateUser(userId);
        return new ModelAndView("redirect:/admin/showUsers");
    }

}
