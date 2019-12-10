package com.konrad.garagev3.service;

import com.konrad.garagev3.exeption.TemplateParseExeption;
import com.konrad.garagev3.model.dao.Template;
import com.konrad.garagev3.model.dao.Vehicle;
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
import java.util.Collections;
import java.util.Locale;
import java.util.Map;

@Service
@Slf4j
public class PDFService {
    @Autowired
    private TemplateRepository templateRepository;
    @Autowired
    @Qualifier("htmlTemplateEngine")
    private TemplateEngine templateEngine;

    public byte[] generatePDF() throws TemplateParseExeption {
        Template vehicle_checkup_remainder = templateRepository.findByType("vehicle_checkup_remainder");
        Context context = new Context(Locale.forLanguageTag("pl"));
//        context.setVariable("vehicle.name", "vehicle");
//        context.setVariable("vehicle.brand", "vehicleBrand");
        context.setVariable("vehicle", Vehicle.builder().brand("test").build());
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
        throw new TemplateParseExeption("PDF can not be generated");
    }

}


