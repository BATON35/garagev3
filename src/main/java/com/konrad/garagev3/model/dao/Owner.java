package com.konrad.garagev3.model.dao;

import lombok.Builder;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@Builder
// TODO: 15.04.2019 no argument constructor and @Builder annotation create problem
public class Owner implements Serializable {
    @Id
    private int id;
//    @OneToMany(
//            mappedBy = "owner",
//            cascade = CascadeType.ALL,
//            orphanRemoval = true
//    )
  //  private List<Vehicle> vehicles;
    private String name;
    private String email;
    private String phoneNumber;
}
