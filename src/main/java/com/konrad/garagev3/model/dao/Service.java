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
public class Service {
    @Id
    @GeneratedValue
    private Long id;
//    @ManyToMany(mappedBy = "services")
//    private List<BodyPriceList> bodyPriceLists;
    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;
    private String description;
}
