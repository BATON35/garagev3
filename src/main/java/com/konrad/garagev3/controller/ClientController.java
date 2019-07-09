package com.konrad.garagev3.controller;

import com.konrad.garagev3.model.dto.ClientDto;
import com.konrad.garagev3.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class ClientController {
    private final ClientService clientService;

    @Autowired
    ClientController(ClientService ownerService) {
        this.clientService = ownerService;
    }

    @GetMapping("/addClient")
    public ModelAndView addClient() {
        //Owner owner = Owner.builder().build();
        //   Vehicle vehicle = Vehicle.builder().build();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("clientDto", new ClientDto());
        //   modelAndView.addObject("vehicle", vehicle);
        modelAndView.setViewName("addClient");
        return modelAndView;
    }

    @GetMapping("/client")
    public String showClients(Model model) {
        model.addAttribute("clients", clientService.findAllActiveClients());
        return "ShowClients";
    }

    @PostMapping("/client")
    public ModelAndView addClient(@Valid ClientDto client, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
//        ClientDto clientExists = clientService.findClientBySurnameAndName(client.getSurname(), client.getName());
        ClientDto clientExists = clientService.findClientByEmail(client.getEmail());
        if (clientExists != null) {
            bindingResult.rejectValue("email", "error.client",
                    "Adres email: " + client.getEmail() + "  znajduje sie już w bazie danych");
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

    @PutMapping("/client/{email}/active")
    public ModelAndView deactivateUser(@PathVariable(value = "email") String email) {
        clientService.deactivateClient(email);
        return new ModelAndView("redirect:/client");
    }
}
