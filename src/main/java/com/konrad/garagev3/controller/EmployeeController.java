package com.konrad.garagev3.controller;

import com.konrad.garagev3.model.*;
import com.konrad.garagev3.model.wraper.OwnerVehicle;
import com.konrad.garagev3.service.OwnerService;
import com.konrad.garagev3.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class EmployeeController {
    private final OwnerService ownerService;

    @Autowired
    EmployeeController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @GetMapping(value = "/employee")
    public String employee() {
        ModelAndView modelAndView = new ModelAndView();
        return "employee";
    }

    @PostMapping("/employee/addClient")
    public ModelAndView addClient(@Valid Owner owner,Vehicle vehicle, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        Owner ownerExists = ownerService.findOwnerByEmail(owner.getEmail());
        if (ownerExists != null) {
            bindingResult.rejectValue("email", "error.user",
                    "Adres email: " + owner.getEmail() + "  znajduje sie już w bazie danych");
        }
        //modelAndView.addObject("client2", new Owner());
        modelAndView.setViewName("addClient");
        return modelAndView;
    }

    @GetMapping("/employee/addClient")
    public String addOwner(Model model) {
        Owner owner = new Owner();
        model.addAttribute("client", owner);
      //  model.addAttribute("vehicle", new Vehicle());
        return "addClient";
    }
}
