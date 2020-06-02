package com.konrad.garagev3.model.response;

import com.konrad.garagev3.model.dto.CarServiceDto;
import com.konrad.garagev3.model.dto.PartDto;
import com.konrad.garagev3.model.dto.WorkerDto;
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
public class JobHistory {
    private List<PartDto> partsDto;
    private CarServiceDto carServiceDto;
    private WorkerDto workerDto;
    private LocalDateTime createdDate;
    private BigDecimal price;
}
