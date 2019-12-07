package com.konrad.garagev3.model.dao;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@EqualsAndHashCode
public class Part {
    @Id
    @GeneratedValue
    private Long id;
    @Column(unique = true)
    private String name;
    private BigDecimal price;
}
