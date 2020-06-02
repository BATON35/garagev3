package com.konrad.garagev3.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class JobDto {
    private long id;
    private List<Long> partIds;
    private Long workerId;
    private Long ServiceId;
    private String vehicleNumberPlate;
}
