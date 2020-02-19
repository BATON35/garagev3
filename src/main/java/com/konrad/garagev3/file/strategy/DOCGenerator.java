package com.konrad.garagev3.file.strategy;

import com.konrad.garagev3.file.model.FileType;
import com.konrad.garagev3.model.dao.Job;
import com.konrad.garagev3.model.dao.Part;
import com.konrad.garagev3.model.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.wp.usermodel.HeaderFooterType;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

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
        table.setCellMargins(100, 200, 100, 200);
        XWPFTableRow columnName = table.getRow(0);
        columnName.getCell(0).setText("Job Id");
        columnName.addNewTableCell().setText("Job Date");
        columnName.addNewTableCell().setText("Service name");
        columnName.addNewTableCell().setText("Parts");
        columnName.addNewTableCell().setText("Price");
        byVehicleId.forEach(job -> {
            XWPFTableRow row = table.createRow();
            row.getCell(0).setText(job.getId().toString());
            row.getCell(1).setText(job.getCreatedDate().format(DateTimeFormatter.ISO_LOCAL_DATE).toString());
            row.getCell(2).setText(job.getCarService().getName());
            row.getCell(3).setText(job.getParts().stream().map(Part::getName).collect(Collectors.joining(" ")));
            row.getCell(4).setText(job.getParts().stream().map(part -> part.getPrice()).reduce(BigDecimal.ZERO, BigDecimal::add).toString());


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
