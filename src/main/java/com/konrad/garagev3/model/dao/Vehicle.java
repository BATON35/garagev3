package com.konrad.garagev3.model.dao;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

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
    @Column(unique = true)
    private String numberPlate;
    private LocalDate overviewDate;
    @OneToMany( mappedBy = "vehicle")
    @JsonBackReference
    private List<Job> servicesPart;
    @ManyToOne
    @JoinColumn(name = "client_id")
    @JsonBackReference
    @EqualsAndHashCode.Exclude
    private Client client;
    private boolean notification;
    @OneToMany(mappedBy = "vehicle")
    private List<Photo> photos;
}
