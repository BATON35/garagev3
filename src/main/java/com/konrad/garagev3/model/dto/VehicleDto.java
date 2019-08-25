package com.konrad.garagev3.model.dto;

import com.konrad.garagev3.model.dao.Client;
import com.konrad.garagev3.model.dao.Workshop;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VehicleDto {
    private Long id;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate productionDate;
    @Length(min = 3, max = 127, message = "Pole imie wymaga conajmniej trzech znakow")
    private String brand;
    private String model;
    private String numberPlate;
//    private Workshop workshop;
    private Client client;
    //private List<Service> services;


}
