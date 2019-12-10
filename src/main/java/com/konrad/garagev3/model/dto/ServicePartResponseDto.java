package com.konrad.garagev3.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ServicePartResponseDto {
    private List<PartDto> partDto;
    private CarServiceDto carServiceDto;
    private LocalDateTime createdDate;
}
