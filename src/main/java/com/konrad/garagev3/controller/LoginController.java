package com.konrad.garagev3.controller;

import com.konrad.garagev3.model.dao.AnonymousUserQuestion;
import com.konrad.garagev3.model.dao.User;
import com.konrad.garagev3.model.dto.UserDto;
import com.konrad.garagev3.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

//@Controller
public class LoginController {

//    @Autowired
//    private UserService userService;
//
//    @RequestMapping(value = "/login", method = RequestMethod.GET)
//    public ModelAndView login() {
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("login");
//        return modelAndView;
//    }
//
//
//    @RequestMapping(value = "/registration", method = RequestMethod.GET)
//    public ModelAndView registration() {
//        ModelAndView modelAndView = new ModelAndView();
//        UserDto userDto = new UserDto();
//        modelAndView.addObject("user", userDto);
//        modelAndView.setViewName("registration");
//        return modelAndView;
//    }
//
//    @RequestMapping(value = "/registration", method = RequestMethod.POST)
//    public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {
//        ModelAndView modelAndView = new ModelAndView();
//        UserDto userExists = userService.findUserByEmail(user.getEmail());
//        if (userExists != null) {
//            bindingResult
//                    .rejectValue("email", "error.user",
//                            "There is already a user registered with the email provided");
//        }
//        if (bindingResult.hasErrors()) {
//            modelAndView.setViewName("registration");
//        } else {
//            userService.SaveVehicle(user);
//            modelAndView.addObject("successMessage", "User has been registered successfully");
//            modelAndView.addObject("user", new User());
//            modelAndView.setViewName("login");
//
//        }
//        return modelAndView;
//    }
//
//    @PostMapping (value = "/mail")
//    public String  mail(Model model) {
//        model.addAttribute("nazwaKlasy", new AnonymousUserQuestion());
//        return "contact";
//    }
}

