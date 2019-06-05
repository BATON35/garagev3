package com.konrad.garagev3.controller;

import com.konrad.garagev3.model.dao.Client;
import com.konrad.garagev3.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class ClientController {
    private final ClientService clientService;

    @Autowired
    ClientController(ClientService ownerService) {
        this.clientService = ownerService;
    }

    @PostMapping("/client")
    public ModelAndView addClient(@Valid Client client, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        //Client clientExists = clientService.findClientByEmail(client.getEmail());
        Client clientExists = clientService.findClientBySurnameAndName(client.getSurname(), client.getName());
        if (clientExists != null) {
            bindingResult.rejectValue("surname", "error.client",
                    "Klient: " + client.getName() + " " + client.getSurname() + "  znajduje sie już w bazie danych");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("addClient");
        } else {
            clientService.saveClient(client);
            modelAndView.addObject("successMessage", "Dodano nowego klienta");
            modelAndView.setViewName("addClient");
        }
        return modelAndView;
    }

    @GetMapping("/addClient")
    public ModelAndView addClient() {
        //Owner owner = Owner.builder().build();
        //   Vehicle vehicle = Vehicle.builder().build();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("client", new Client());
        //   modelAndView.addObject("vehicle", vehicle);
        modelAndView.setViewName("addClient");
        return modelAndView;
    }
}
