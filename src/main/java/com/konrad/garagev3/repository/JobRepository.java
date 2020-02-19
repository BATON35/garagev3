package com.konrad.garagev3.repository;

import com.konrad.garagev3.model.dao.Job;
import com.konrad.garagev3.model.dao.Part;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobRepository extends JpaRepository<Job, Long> {
    List<Job> findByVehicleId(Long id);
    List<Job> findByVehicleNumberPlate(String numberPlate);
}

