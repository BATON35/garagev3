package com.konrad.garagev3.repository;

import com.konrad.garagev3.model.dao.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PhotoRepository extends JpaRepository<Photo, Long> {

    List<Photo> findByVehicleId(Long id);
}
