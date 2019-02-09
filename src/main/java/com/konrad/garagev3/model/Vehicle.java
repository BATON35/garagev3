package com.konrad.garagev3.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
   // @Column(name = "vehicle_id")
    private int id;
    private Date productionDate;
    private String brand;
    private String model;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "workshop_id")
    private Workshop workshop;
    @OneToMany(
            mappedBy = "vehicle",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Service> services;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private Owner owner;
}
