package com.konrad.garagev3.model.dao;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
public class Workshop implements Serializable {
    @Id
    private int id;
    private String name;
    //    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private Address address;
    @OneToMany(
            mappedBy = "workshop",
            orphanRemoval = true
    )
    private List<Vehicle> vehicles;
//    @OneToMany(
//            mappedBy = "workshop",
//            orphanRemoval = true
//    )
//    private List<User> users;
}
