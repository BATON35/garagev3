package com.konrad.garagev3.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Data
@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
   // @Column(name = "Address_id")
    private int id;
    //@Column(name="city")
    private String city;
  //  @Column(name="street")
    private String street;
  //  @Column(name="postcode")
    private String postcode;
}
