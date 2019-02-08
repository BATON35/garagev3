package com.konrad.garagev3.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private Date productionDate;
    private String brand;
    private String model;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "workshop_id")
    private Workshop workshop;
    //private Service service;
}
