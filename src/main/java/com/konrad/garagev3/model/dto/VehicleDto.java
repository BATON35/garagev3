package com.konrad.garagev3.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VehicleDto implements Serializable {
    private Long id;
 //   @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String productionDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate overviewDate;
    @Length(min = 3, max = 127, message = "Pole imie wymaga conajmniej trzech znakow")
    private String brand;
    private String model;
    private String numberPlate;
    private boolean notification;
    private boolean hasHistory;



}
