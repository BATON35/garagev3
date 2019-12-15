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
    @OneToMany( mappedBy = "vehicle")
    private List<ServicePart> servicesPart;
    @ManyToOne
    @JoinColumn(name = "client_id")
    @JsonBackReference
    @EqualsAndHashCode.Exclude
    private Client client;
    private boolean notification;
    @OneToMany(mappedBy = "vehicle")
    private List<Photo> photos;
}
