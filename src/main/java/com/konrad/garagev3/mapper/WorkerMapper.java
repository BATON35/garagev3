package com.konrad.garagev3.mapper;

import com.konrad.garagev3.model.dao.Worker;
import com.konrad.garagev3.model.dto.WorkerDto;
import org.mapstruct.Mapper;

@Mapper
public interface WorkerMapper {
    WorkerDto toWorkerDto(Worker worker);
    Worker toWorker(WorkerDto workerDto);

}
