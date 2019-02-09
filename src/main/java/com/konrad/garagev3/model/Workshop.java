package com.konrad.garagev3.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
public class Workshop {
    @Id
  //  @GeneratedValue(strategy = GenerationType.AUTO)
 //   @Column(name = "workshop_id")
    private int id;
 //   @Column(name = "name")
    private String name;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @MapsId
    private Address address;
    @OneToMany(
            mappedBy = "workshop",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Vehicle> vehicles;
}
