package com.konrad.garagev3.file.strategy;

import com.konrad.garagev3.file.model.FileType;
import com.konrad.garagev3.model.dao.Job;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Slf4j
@Component
public class XLSGenerator extends FileStrategy {
    public XLSGenerator() {
        super(FileType.XLS);
    }

    @Override
    public byte[] generateVehicleHistoryReport(String numberPlate) {
        List<Job> byVehicleId = jobRepository.findByVehicleNumberPlate(numberPlate);
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("report");
        HSSFRow row = sheet.createRow(0);
        row.createCell(0).setCellValue("Id");
        row.createCell(1).setCellValue("Date");
        row.createCell(2).setCellValue("Service");
        row.createCell(3).setCellValue("Parts");
        Integer rowCount = 1;
        for (int i = 0; i < byVehicleId.size(); i++) {
           row = sheet.createRow(rowCount++);
           row.createCell(0).setCellValue(byVehicleId.get(i).getId());
           row.createCell(1).setCellValue(byVehicleId.get(i).getCreatedDate());
           row.createCell(2).setCellValue(byVehicleId.get(i).getCarService().toString());
           row.createCell(3).setCellValue(byVehicleId.get(i).getParts().toString());
        }
        sheet.setAutoFilter(new CellRangeAddress(0, rowCount, 0, 1));
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            workbook.write(byteArrayOutputStream);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return byteArrayOutputStream.toByteArray();
    }
}
