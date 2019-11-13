package com.konrad.garagev3.controller;

import com.konrad.garagev3.model.dao.ServicePart;
import com.konrad.garagev3.model.dto.ServicePartDto;
import com.konrad.garagev3.service.ServicePartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/service-part")
public class ServicePartControllerRest {
    @Autowired
    private ServicePartService servicePartService;

    @PostMapping()
    public ServicePart saveServicePart(@RequestBody ServicePartDto servicePartDto) {
        return servicePartService.saveServicePart(servicePartDto.getWorkerId(), servicePartDto.getPartIds(), servicePartDto.getServiceId());
    }

}
