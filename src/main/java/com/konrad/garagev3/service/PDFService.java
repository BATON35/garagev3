package com.konrad.garagev3.service;

import com.konrad.garagev3.exeption.TemplateParseException;
import com.konrad.garagev3.model.dao.Job;
import com.konrad.garagev3.model.dao.Template;
import com.konrad.garagev3.repository.JobRepository;
import com.konrad.garagev3.repository.TemplateRepository;
import com.lowagie.text.DocumentException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.*;
import java.util.List;
import java.util.Locale;

@Service
@Slf4j
public class PDFService {
    @Autowired
    private TemplateRepository templateRepository;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    @Qualifier("htmlTemplateEngine")
    private TemplateEngine templateEngine;

    public byte[] generatePDF(Long vehicleId) throws TemplateParseException {
        Template vehicle_checkup_remainder = templateRepository.findByType("vehicle_checkup_remainder");
        Context context = new Context(Locale.forLanguageTag("pl"));
        List<Job> byVehicleId = jobRepository.findByVehicleId(vehicleId);
        context.setVariable("vehicle", byVehicleId.get(0).getVehicle());
        String mailMessage = templateEngine.process(vehicle_checkup_remainder.getBody(), context);
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
        throw new TemplateParseException("PDF can not be generated");
    }

}


