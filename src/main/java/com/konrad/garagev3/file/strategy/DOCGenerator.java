package com.konrad.garagev3.file.strategy;

import com.konrad.garagev3.file.model.FileType;
import com.konrad.garagev3.model.dao.Job;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Slf4j
@Component
public class DOCGenerator extends FileStrategy {
    DOCGenerator() {
        super(FileType.DOC);
    }

    @Override
    public byte[] generateVehicleHistoryReport(String numberPlate) {
        List<Job> byVehicleId = jobRepository.findByVehicleNumberPlate(numberPlate);
        XWPFDocument document = new XWPFDocument();
        XWPFTable table = document.createTable();
        XWPFTableRow row = table.getRow(0);
        row.getCell(0).setText("Id");
        row.addNewTableCell().setText("Date");
        row.addNewTableCell().setText("Service");
        row.addNewTableCell().setText("Parts");
        byVehicleId.forEach(job -> {
            XWPFTableRow row1 = table.createRow();
            row1.getCell(0).setText(job.getId().toString());
            row1.getCell(1).setText(job.getCreatedDate().toString());
            row1.getCell(2).setText(job.getCarService().toString());
            row1.getCell(3).setText(job.getParts().toString());
        });
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            document.write(byteArrayOutputStream);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return byteArrayOutputStream.toByteArray();
    }
}
