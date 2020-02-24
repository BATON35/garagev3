package com.konrad.garagev3.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class JobResponseDto {
    private List<PartDto> partsDto;
    private CarServiceDto carServiceDto;
    private WorkerDto workerDto;
    private LocalDateTime createdDate;
    private BigDecimal price;
}
