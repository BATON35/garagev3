package com.konrad.garagev3.repository;

import com.konrad.garagev3.model.dao.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    Vehicle findByNumberPlate(String numberPlate);

    void deleteByClientId(Long id);

    List<Vehicle> findByClientId(Long id);
}
