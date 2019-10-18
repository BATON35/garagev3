package com.konrad.garagev3.model.dao;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@EqualsAndHashCode
public class Vehicle {
    @Id
    @GeneratedValue
    private Long id;
    //  private Date productionDate;
    private String brand;
    private String model;
    private String numberPlate;
    private LocalDate overviewDate;
    //    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "workshop_id")
//    private Workshop workshop;
    @OneToMany(mappedBy = "vehicle", orphanRemoval = true,fetch = FetchType.EAGER)
    private List<Service> services;
    @ManyToOne
    @JoinColumn(name = "client_id")
    @JsonBackReference
    @EqualsAndHashCode.Exclude
    private Client client;
    private boolean notification;

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Vehicle vehicle = (Vehicle) o;
//        return Objects.equals(id, vehicle.id) &&
//                Objects.equals(brand, vehicle.brand) &&
//                Objects.equals(model, vehicle.model) &&
//                Objects.equals(numberPlate, vehicle.numberPlate);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id, brand, model, numberPlate);
//    }
}
