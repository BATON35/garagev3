package com.konrad.garagev3.model.dao;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@EqualsAndHashCode
public class CarService {
    @Id
    @GeneratedValue
    private Long id;
//    @ManyToMany(mappedBy = "carServices")
//    private List<BodyPriceList> bodyPriceLists;
    private String description;
    private String name;
    private BigDecimal price;
}
