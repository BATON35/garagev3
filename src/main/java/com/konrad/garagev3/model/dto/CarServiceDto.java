package com.konrad.garagev3.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CarServiceDto {
    private Long id;
    private String description;
    private String name;
    private BigDecimal price;
}
