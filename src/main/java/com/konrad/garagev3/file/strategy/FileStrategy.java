package com.konrad.garagev3.file.strategy;

import com.konrad.garagev3.file.model.FileType;
import com.konrad.garagev3.repository.JobRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class FileStrategy {
    @Autowired
    protected JobRepository jobRepository;
    @Getter
    private FileType fileType;

    public FileStrategy(FileType fileType) {
        this.fileType = fileType;
    }

    public abstract byte[] generateVehicleHistoryReport(Long vehicleId);

}
