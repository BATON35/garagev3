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
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,orphanRemoval = true, mappedBy = "client")
    @JsonManagedReference
    @EqualsAndHashCode.Exclude
    private Set<Vehicle> vehicles;
    private String name;
    private Integer active;
    private String surname;
    private String email;
    private String phoneNumber;

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Client client = (Client) o;
//        return Objects.equals(id, client.id) &&
//                Objects.equals(name, client.name) &&
//                Objects.equals(active, client.active) &&
//                Objects.equals(surname, client.surname) &&
//                Objects.equals(email, client.email) &&
//                Objects.equals(phoneNumber, client.phoneNumber);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id, name, active, surname, email, phoneNumber);
//    }
}
