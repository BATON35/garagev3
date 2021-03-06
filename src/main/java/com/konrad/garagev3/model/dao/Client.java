package com.konrad.garagev3.model.dao;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.Objects;
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
    protected boolean deleted;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "client")
    @JsonManagedReference
    @EqualsAndHashCode.Exclude
    private Set<Vehicle> vehicles;
    private String name;
    private String surname;
    @Column(unique = true)
    private String email;
    private String phoneNumber;
}
