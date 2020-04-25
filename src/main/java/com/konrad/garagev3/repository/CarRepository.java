package com.konrad.garagev3.repository;

import com.konrad.garagev3.model.dao.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CarRepository  extends JpaRepository<Car, Long> {
    Optional<Car> findByBrandAndModel(String brand, String model);
    @Query(value = "select brand from car group by brand", nativeQuery = true)
    List<String> findAllBrand();

    @Query(value = "select model from car where brand = ?1 group by model", nativeQuery = true)
    List<String> findAllModelByBrand(String brand);

    @Query(value = "select production_date from car where brand = ?1 and model = ?2", nativeQuery = true)
    List<String> findProductionDateByBrandAndModel(String brand, String model);
}
