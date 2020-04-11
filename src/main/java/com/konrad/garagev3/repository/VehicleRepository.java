package com.konrad.garagev3.repository;

import com.konrad.garagev3.model.dao.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    Optional<Vehicle> findByNumberPlate(String numberPlate);

    void deleteByClientId(Long id);

    List<Vehicle> findByClientId(Long id);

    List<Vehicle> findByOverviewDate(LocalDate currentDate);

    List<Vehicle> findByNumberPlateContaining(String text);

    @Query(value = "select distinct brand from car", nativeQuery = true)
    List<String> distinctBrand();

    @Query(value = "select distinct model from car where brand = ?1", nativeQuery = true)
    List<String> distinctModel(String brand);


}
