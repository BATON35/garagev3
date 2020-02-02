package com.konrad.garagev3.file.strategy;

import com.konrad.garagev3.file.model.FileType;
import com.konrad.garagev3.model.dao.Job;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CSVGenerator extends FileStrategy {
    public CSVGenerator() {
        super(FileType.CSV);
    }

    @Override
    public byte[] generateVehicleHistoryReport(String numberPlate) {
        List<Job> byVehicleId = jobRepository.findByVehicleNumberPlate(numberPlate);
//        List<Job> byVehicleId = jobRepository.findByVehicleId(vehicleId);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Id;");
        stringBuilder.append("Date;");
        stringBuilder.append("Service;");
        stringBuilder.append("Parts\n");
        byVehicleId.forEach(e ->
            stringBuilder
                    .append(e.getId() + ";")
                    .append(e.getCreatedDate() + ";")
                    .append(e.getCarService() + ";")
                    .append(e.getParts() + "\n")
        );
        return stringBuilder.toString().getBytes();
    }
}
