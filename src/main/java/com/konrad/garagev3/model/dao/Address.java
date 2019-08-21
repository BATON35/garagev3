package com.konrad.garagev3.model.dao;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@EqualsAndHashCode
public class Address {
    @Id
    @GeneratedValue
    private Long id;
    private String city;
    private String street;
    private String postcode;
}
