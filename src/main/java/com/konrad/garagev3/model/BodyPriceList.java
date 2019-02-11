package com.konrad.garagev3.model;

import com.konrad.garagev3.model.Service;

import javax.persistence.*;
import java.util.List;

@Entity
public class BodyPriceList {
    
    @Id
   // @Column(name = "id_rate")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
   @ManyToMany(cascade = {
           CascadeType.PERSIST,
           CascadeType.MERGE
   })
   @JoinTable(name = "service_rate",
           joinColumns = @JoinColumn(name = "rate_id"),
           inverseJoinColumns = @JoinColumn(name = "service_id")
   )
    private List<Service> services;
}
