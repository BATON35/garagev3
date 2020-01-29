package com.konrad.garagev3.controller;

import com.konrad.garagev3.exeption.TemplateParseException;
import com.konrad.garagev3.file.FileFactory;
import com.konrad.garagev3.file.model.FileType;
import com.konrad.garagev3.service.FileService;
import com.konrad.garagev3.service.PDFService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@RestController
@RequestMapping("/api/file")
public class FileController {
    @Autowired
    private PDFService pdfService;
    @Autowired
    private FileService fileService;
    @Autowired
    private FileFactory fileFactory;


    @GetMapping("/{vehicleId}")
    public ResponseEntity<byte[]> getPDF(@PathVariable Long vehicleId) throws TemplateParseException {
        byte[] bytes = pdfService.generatePDF(vehicleId);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Content-Type", MediaType.APPLICATION_PDF_VALUE);
        httpHeaders.set("Content-Length", Integer.toString(bytes.length));
        httpHeaders.set("Content-Disposition", "attachment;filename=test.pdf");
        return new ResponseEntity<>(bytes, httpHeaders, HttpStatus.CREATED);
    }

    @PostMapping()
    public void uploadFotoCar(@RequestParam List<MultipartFile> multipartFile, @RequestParam Long vehicleId) {
        fileService.uploadPhotoCar(multipartFile, vehicleId);
    }

    @GetMapping("{vehicleId}/{fileType}")
    public ResponseEntity<byte[]> getGeneratedVehicleHistoryReport(@PathVariable long vehicleId, @PathVariable FileType fileType) {
        byte[] bytes = fileFactory.getStrategy(fileType).generateVehicleHistoryReport(vehicleId);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Content-Type", MediaType.APPLICATION_OCTET_STREAM_VALUE);
        httpHeaders.set("Content-Length", Integer.toString(bytes.length));
        httpHeaders.set("Content-Disposition", "attachment;filename=test." + fileType.toString().toLowerCase());
        return new ResponseEntity<>(bytes, httpHeaders, HttpStatus.CREATED);
    }

}
