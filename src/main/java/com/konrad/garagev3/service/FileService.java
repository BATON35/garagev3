package com.konrad.garagev3.service;

import com.konrad.garagev3.model.dao.Photo;
import com.konrad.garagev3.repository.PhotoRepository;
import com.konrad.garagev3.repository.VehicleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
@Slf4j
public class FileService {
    @Value("${vehicle.photo.path}")
    private String vehiclePhotoFilePath;
    @Autowired
    private VehicleRepository vehicleRepository;
    @Autowired
    private PhotoRepository photoRepository;


    public void uploadPhotoCar(List<MultipartFile> multipartFiles, Long vehicleId) {
        vehicleRepository.findById(vehicleId).ifPresent(vehicle -> {
            multipartFiles.forEach(f -> {
                File file = new File(vehiclePhotoFilePath + "/" + vehicleId + "/" + f.getOriginalFilename());
                if (!file.exists()) {
                    file.getParentFile().mkdirs();
                }
                try {
                    f.transferTo(file);
                    photoRepository.save(Photo.builder()
                            .vehicle(vehicle)
                            .link(file.getAbsolutePath()).build());
                } catch (IOException e) {
                    log.error(e.getMessage(), e);
                }
            });
        });
    }
}
