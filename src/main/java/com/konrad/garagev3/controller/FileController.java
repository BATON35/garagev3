package com.konrad.garagev3.controller;

import com.konrad.garagev3.exeption.TemplateParseExeption;
import com.konrad.garagev3.service.PDFService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/pdf")
public class FileController {
    @Autowired
    private PDFService pdfService;

    @GetMapping
    public ResponseEntity<byte[]> getPDF() throws TemplateParseExeption {
        byte[] bytes = pdfService.generatePDF();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Content-Type", MediaType.APPLICATION_PDF_VALUE);
        httpHeaders.set("Content-Length", Integer.toString(bytes.length));
        httpHeaders.set("Content-Disposition", "attachment;filename=test.pdf");
        return new ResponseEntity<>(bytes, httpHeaders, HttpStatus.CREATED);
    }
}
