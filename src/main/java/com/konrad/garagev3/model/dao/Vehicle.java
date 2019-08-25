package com.konrad.garagev3.model.dao;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@EqualsAndHashCode
public class Vehicle {
    @Id
    @GeneratedValue
    private Long id;
    private Date productionDate;
    private String brand;
    private String model;
    private String numberPlate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "workshop_id")
    private Workshop workshop;
    @OneToMany(mappedBy = "vehicle", orphanRemoval = true)
    private List<Service> services;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn
    private Client client;
}
