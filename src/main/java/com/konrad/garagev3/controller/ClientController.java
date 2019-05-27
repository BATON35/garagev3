package com.konrad.garagev3.controller;

import com.konrad.garagev3.model.dao.Owner;
import com.konrad.garagev3.model.dao.Vehicle;
import com.konrad.garagev3.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class ClientController {
    private final OwnerService ownerService;

    @Autowired
    ClientController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @PostMapping("/client")
    public ModelAndView addClient(@Valid Owner owner, Vehicle vehicle, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        Owner ownerExists = ownerService.findOwnerByEmail(owner.getEmail());
        if (ownerExists != null) {
            bindingResult.rejectValue("email", "error.user",
                    "Adres email: " + owner.getEmail() + "  znajduje sie już w bazie danych");
        }
        modelAndView.setViewName("addClient");
        return modelAndView;
    }

    @RequestMapping("/addClient")
    public String addOwner() {
//        Owner owner = Owner.builder().build();
//        model.addAttribute("client", owner);
        //  model.addAttribute("vehicle", new Vehicle());
        return "addClient";
    }
}
