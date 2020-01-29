package com.konrad.garagev3.file.strategy;

import com.konrad.garagev3.exeption.TemplateParseException;
import com.konrad.garagev3.file.model.FileType;
import com.konrad.garagev3.model.dao.Job;
import com.konrad.garagev3.model.dao.Template;
import com.konrad.garagev3.repository.JobRepository;
import com.konrad.garagev3.repository.TemplateRepository;
import com.lowagie.text.DocumentException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Locale;

@Component
@Slf4j
public class PdfGenerator extends FileStrategy {
    @Autowired
    private TemplateRepository templateRepository;

    @Autowired
    @Qualifier("htmlTemplateEngine")
    private TemplateEngine templateEngine;
    public PdfGenerator() {
        super(FileType.PDF);
    }

    @Override
    public byte[] generateVehicleHistoryReport(Long vehicleId) {
        Template vehicle_checkup_remainder = templateRepository.findByType("vehicle_checkup_remainder");
        Context context = new Context(Locale.forLanguageTag("pl"));
        List<Job> byVehicleId = jobRepository.findByVehicleId(vehicleId);
        context.setVariable("vehicle", byVehicleId.get(0).getVehicle());
        String mailMessage = templateEngine.process(vehicle_checkup_remainder.getTemplate(), context);
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ITextRenderer iTextRenderer = new ITextRenderer();
            iTextRenderer.setDocumentFromString(mailMessage);
            iTextRenderer.layout();
            iTextRenderer.createPDF(byteArrayOutputStream, false);
            iTextRenderer.finishPDF();
            return byteArrayOutputStream.toByteArray();
        } catch (DocumentException e) {
            log.error(e.getMessage(), e);
        }

        return new byte[0];
    }
}
