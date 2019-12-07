package com.konrad.garagev3.model.dto;

import com.konrad.garagev3.model.dao.WorkerType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@Data
@SuperBuilder
public class WorkerDto extends UserDto{
    private WorkerType type;
}
