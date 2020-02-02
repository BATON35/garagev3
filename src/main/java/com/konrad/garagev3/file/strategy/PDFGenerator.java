package com.konrad.garagev3.file.strategy;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.konrad.garagev3.file.model.FileType;
import com.konrad.garagev3.model.dao.Job;
import com.konrad.garagev3.repository.TemplateRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;

import java.io.ByteArrayOutputStream;
import java.util.List;

@Component
@Slf4j
public class PDFGenerator extends FileStrategy {
    @Autowired
    private TemplateRepository templateRepository;

    @Autowired
    @Qualifier("htmlTemplateEngine")
    private TemplateEngine templateEngine;

    public PDFGenerator() {
        super(FileType.PDF);
    }

    @Override
    public byte[] generateVehicleHistoryReport(String numberPlate) {
        List<Job> byVehicleId = jobRepository.findByVehicleId(4L);
        Document document = new Document(PageSize.A4, 10f, 10f, 10f, 10f);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            PdfWriter.getInstance(document, byteArrayOutputStream);
            document.open();
            PdfPTable pdfPTable = new PdfPTable(4);
            pdfPTable.addCell("id");
            pdfPTable.addCell("Date");
            pdfPTable.addCell("Service");
            pdfPTable.addCell("Parts");
            byVehicleId.forEach(e -> {
                pdfPTable.addCell(e.getId().toString());
                pdfPTable.addCell(e.getCreatedDate().toString());
                pdfPTable.addCell(e.getCarService().toString());
                pdfPTable.addCell(e.getParts().toString());
            });
            document.add(pdfPTable);
            document.close();
        } catch (DocumentException e) {
            log.error(e.getMessage());
        }


        return byteArrayOutputStream.toByteArray();
    }
}
