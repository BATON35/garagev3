package com.konrad.garagev3.repository;

import com.konrad.garagev3.model.dao.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    Optional<Vehicle> findByNumberPlate(String numberPlate);

    void deleteByClientId(Long id);

    List<Vehicle> findByClientId(Long id);

    List<Vehicle> findByOverviewDate(LocalDate currentDate);

    List<Vehicle> findByNumberPlateContaining(String text);
}
