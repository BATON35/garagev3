package com.konrad.garagev3.model.dao;

import lombok.*;

import javax.persistence.*;

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

    @Column(unique = true)
    private String link;

    @ManyToOne
    private Vehicle vehicle;
}
