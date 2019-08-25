package com.konrad.garagev3.controller;

import com.konrad.garagev3.model.dto.ClientDto;
import com.konrad.garagev3.model.dto.VehicleDto;
import com.konrad.garagev3.service.ClientService;
import com.konrad.garagev3.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
public class ClientController {
    private final ClientService clientService;
    private final VehicleService vehicleService;

    @Autowired
    ClientController(ClientService ownerService, VehicleService vehicleService) {
        this.clientService = ownerService;
        this.vehicleService = vehicleService;
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


    @GetMapping("/deleteClient")
    public ModelAndView showDeleteClient() {
        ModelAndView modelAndView = new ModelAndView();
        ClientDto client = new ClientDto();
        modelAndView.addObject("clientDto", client);
        modelAndView.setViewName("deleteClient");
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

    @PutMapping("/client/{id}/active")
    public ModelAndView deactivateClient(@PathVariable Long id) {
        clientService.deactivateClient(id);
        return new ModelAndView("redirect:/client");
    }
    @GetMapping("/client/{id}/active")
    public ModelAndView deactivateClient2(@PathVariable Long id) {
        clientService.deactivateClient(id);
        return new ModelAndView("redirect:/client");
    }

    @PostMapping("/editClient")
    public ModelAndView editClient(@Valid ClientDto clientDto) {
        ModelAndView modelAndView = new ModelAndView();
        ClientDto editedClient = clientService.editClient(clientDto);
        modelAndView.addObject("successMessage", "Edytowano klienta");
        modelAndView.addObject(editedClient);
        modelAndView.setViewName("clientDetails");
        return modelAndView;
    }

    @GetMapping("/client/{email}")
    public ModelAndView showClientDetails(@PathVariable String email) {
        ModelAndView modelAndView = new ModelAndView();
        ClientDto clientDto = clientService.findClientByEmail(email);
        List vehicles = vehicleService.findVehicleByClientMail(clientDto.getEmail());
        clientDto.setVehicles(vehicles);
        modelAndView.addObject(clientDto);
        modelAndView.setViewName("clientDetails");
        return modelAndView;
    }


    @GetMapping("/editClient/{email}")
    public ModelAndView editClient(@PathVariable String email) {
        ModelAndView modelAndView = new ModelAndView();
        ClientDto clientDto = clientService.findClientByEmail(email);
        List vehicles = vehicleService.findVehicleByClientMail(clientDto.getEmail());
        clientDto.setVehicles(vehicles);
        modelAndView.addObject(clientDto);
        modelAndView.setViewName("editClient");
        return modelAndView;
    }

    @GetMapping("client/{email}/vehicle")
    public ModelAndView showAddVehicle(@PathVariable String email) {
        ModelAndView modelAndView = new ModelAndView();
        ClientDto clientDto = clientService.findClientByEmail(email);
        modelAndView.addObject(clientDto);
        modelAndView.addObject("vehicleDto", new VehicleDto());
        modelAndView.setViewName("addVehicle");
        return modelAndView;

    }

    @PostMapping("client/{email}/vehicle")
    public ModelAndView addVehicleToClient(@PathVariable String email, @Valid VehicleDto vehicleDto, BindingResult bindingResult, ClientDto clientDto) {
        ModelAndView modelAndView = new ModelAndView();

        VehicleDto vehicleExists = vehicleService.findVehicleByNumberPlate(vehicleDto.getNumberPlate());
        if (vehicleExists != null) {
            bindingResult.rejectValue("numberPlate", "error.vehicleDto",
                    "Pojazd o tych tablicach: " + vehicleDto.getNumberPlate() + "  znajduje sie już w bazie danych");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("addVehicle");
        } else {
            vehicleService.saveVehicle(vehicleDto, email);
            modelAndView.addObject("successMessage", "Dodano pojazd");
            modelAndView.setViewName("addVehicle");
        }
        return modelAndView;
    }

    @DeleteMapping("/client")
    public ModelAndView deleteClient(ClientDto clientDto, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        ClientDto clientExist = clientService.findClientByEmail(clientDto.getEmail());
        if (clientExist == null) {
            bindingResult.rejectValue("email", "error.client",
                    "Utzytkownik o wprowadzonym adresie email nie istnieje");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("deleteClient");
        } else {
            vehicleService.deleteByClientId(clientService.findClientByEmail(clientDto.getEmail()).getId());
            clientService.deleteClient(clientDto.getEmail());
            modelAndView.addObject("successMessage", "User has been deleted successfully");
            modelAndView.addObject("clientDto", new ClientDto());
            modelAndView.setViewName("deleteClient");
        }
        return modelAndView;
    }
}
