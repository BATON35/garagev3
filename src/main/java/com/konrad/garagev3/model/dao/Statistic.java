package com.konrad.garagev3.model.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
public class Statistic {
    @Id
    @GeneratedValue
    private Long id;
    private Double lowerPrice;
    private Double heightPrice;
    private Double avrPrice;

}
