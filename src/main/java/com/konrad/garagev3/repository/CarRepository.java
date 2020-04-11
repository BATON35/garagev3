package com.konrad.garagev3.repository;

import com.konrad.garagev3.model.dao.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CarRepository  extends JpaRepository<Car, Long> {
    Optional<Car> findByBrandAndModel(String brand, String model);
}
