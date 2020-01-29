package com.konrad.garagev3.file.strategy;

import com.konrad.garagev3.file.model.FileType;
import org.springframework.stereotype.Component;

@Component
public class XlsGenerator extends FileStrategy {
    public XlsGenerator() {
        super(FileType.XLS);
    }

    @Override
    public byte[] generateVehicleHistoryReport(Long vehicleId) {
        System.out.println("HLS generated");
        return new byte[0];
    }
}
