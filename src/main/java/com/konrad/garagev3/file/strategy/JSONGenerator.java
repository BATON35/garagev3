package com.konrad.garagev3.file.strategy;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.konrad.garagev3.file.model.FileType;
import com.konrad.garagev3.model.dao.Job;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class JSONGenerator extends FileStrategy {
    @Autowired
    private ObjectMapper objectMapper;

    public JSONGenerator() {
        super(FileType.JSON);
    }

    @Override
    public byte[] generateVehicleHistoryReport(String numberPlate) {
        List<Job> byVehicleId = jobRepository.findByVehicleId(4L);
        try {
            return objectMapper.writeValueAsBytes(byVehicleId);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
        }
        return new byte[0];
    }
}
