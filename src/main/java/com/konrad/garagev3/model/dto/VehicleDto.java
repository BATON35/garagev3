package com.konrad.garagev3.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VehicleDto {
    private Long id;
 //   @DateTimeFormat(pattern = "yyyy-MM-dd")
   // private LocalDate productionDate;
    @Length(min = 3, max = 127, message = "Pole imie wymaga conajmniej trzech znakow")
    private String brand;
    private String model;
    private String numberPlate;
//    private Workshop workshop;
 //   private long Client_id;
 //   private Client client;
    //private List<Service> services;


}
