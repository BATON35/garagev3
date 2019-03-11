package com.konrad.garagev3.model.dao;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Service implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    //@Column(name = "service_id")
    private int id;
    @ManyToMany(mappedBy = "services")
    private List<BodyPriceList> bodyPriceLists;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;
    private String description;
}
