package com.konrad.garagev3.model.dao;

import javax.persistence.*;
import java.util.List;

@Entity
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    //@Column(name = "service_id")
    private long id;
    @ManyToMany(mappedBy = "services")
    private List<BodyPriceList> bodyPriceLists;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;
    private String description;
}
