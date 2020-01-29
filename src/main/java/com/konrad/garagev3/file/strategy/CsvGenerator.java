package com.konrad.garagev3.file.strategy;

import com.konrad.garagev3.file.model.FileType;
import org.springframework.stereotype.Component;

@Component
public class CsvGenerator extends FileStrategy{
    public CsvGenerator() {
        super(FileType.CSV);
    }

    @Override
    public byte[] generateVehicleHistoryReport(Long vehicleId) {
        System.out.println("csv generated");
        return new byte[0];
    }
}
