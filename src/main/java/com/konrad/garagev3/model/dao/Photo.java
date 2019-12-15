package com.konrad.garagev3.model.dao;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
public class Photo {
    @Id
    @GeneratedValue
    private Long id;

    private String description;

    private String link;

    @ManyToOne
    private Vehicle vehicle;
}
