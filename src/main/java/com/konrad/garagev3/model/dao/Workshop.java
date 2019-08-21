package com.konrad.garagev3.model.dao;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@EqualsAndHashCode
public class Workshop  {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    //    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    private Address address;
    @OneToMany(mappedBy = "workshop", orphanRemoval = true)
    private List<Vehicle> vehicles;
//    @OneToMany(
//            mappedBy = "workshop",
//            orphanRemoval = true
//    )
//    private List<User> users;
}
