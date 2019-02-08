package com.konrad.garagev3.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Rate {
    @Id
    @Column(name = "id_rate")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @ManyToMany(mappedBy = "rates")
    private List<Service> services;
}
