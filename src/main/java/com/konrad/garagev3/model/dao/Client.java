package com.konrad.garagev3.model.dao;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@EqualsAndHashCode
public class Client {
    @Id
    @GeneratedValue
    private Long id;
    // @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @OneToMany(mappedBy = "client")
    private Set<Vehicle> vehicles;
    private String name;
    private Integer active;
    private String surname;
    private String email;
    private String phoneNumber;
}
