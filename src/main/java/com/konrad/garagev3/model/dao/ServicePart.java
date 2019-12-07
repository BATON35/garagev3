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
public class ServicePart {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private CarService carService;

    @ManyToMany
    @JoinTable
    private List<Part> parts;

    @ManyToOne
    private Worker worker;

    @ManyToOne
    private Vehicle vehicle;
}
