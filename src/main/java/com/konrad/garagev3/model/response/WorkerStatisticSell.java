package com.konrad.garagev3.model.response;

import lombok.*;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WorkerStatisticSell {
    private String date;
    private BigDecimal price;
    private String name;
}
