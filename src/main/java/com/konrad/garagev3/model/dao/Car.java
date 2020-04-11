package com.konrad.garagev3.model.dao;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@ToString
public class Car {
    @Id
    @GeneratedValue
    private Long Id;
    private String productionDate;
    private String brand;
    private String model;
    private Double lowerPrice;
    private Double heightPrice;
    private Double avrPrice;
}
