package com.konrad.garagev3.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ServicePartDto {
    private List<Long> partIds;
    private Long workerId;
    private Long ServiceId;
}
