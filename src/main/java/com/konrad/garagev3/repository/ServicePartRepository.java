package com.konrad.garagev3.repository;

import com.konrad.garagev3.model.dao.ServicePart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServicePartRepository extends JpaRepository<ServicePart, Long> {
    List<ServicePart> findByVehicleId(Long id);
}
