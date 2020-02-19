package com.konrad.garagev3.model.dao;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@EqualsAndHashCode
@EntityListeners(AuditingEntityListener.class)
public class Vehicle {
    @Id
    @GeneratedValue
    private Long id;
    private LocalDate productionDate;
    private String brand;
    private String model;
    @Column(unique = true)
    private String numberPlate;
    private LocalDate overviewDate;
    @OneToMany( mappedBy = "vehicle")
    @JsonBackReference
    private List<Job> jobs;
    @ManyToOne
    @JoinColumn(name = "client_id")
    @JsonBackReference
    @EqualsAndHashCode.Exclude
    private Client client;
    private boolean notification;
    @OneToMany(mappedBy = "vehicle")
    @JsonIgnore
    private List<Photo> photos;
    @CreatedBy
    private String createdBy;
    @CreatedDate
    private LocalDateTime createdDate;
    @LastModifiedDate
    private LocalDateTime lastModifiedDate;
    @LastModifiedBy
    private String lastModifiedBy;
}
