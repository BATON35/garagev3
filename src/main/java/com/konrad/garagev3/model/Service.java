package com.konrad.garagev3.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    //@Column(name = "service_id")
    private int id;
    @ManyToMany(mappedBy = "services")
    private List<Rate> rates;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;
}
