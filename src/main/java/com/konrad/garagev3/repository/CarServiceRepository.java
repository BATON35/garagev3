package com.konrad.garagev3.repository;

import com.konrad.garagev3.model.dao.CarService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CarServiceRepository extends JpaRepository<CarService, Long> {
    @Query(value = "select c.name from car_service c where c.name like CONCAT(:searchText,'%')", nativeQuery = true)
    List<String> findByAutoCompleteName(@Param(value = "searchText") String searchText);

    List<CarService> findByNameContaining(String searchText);
}
