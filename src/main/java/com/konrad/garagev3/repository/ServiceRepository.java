package com.konrad.garagev3.repository;

import com.konrad.garagev3.model.dao.CarService;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository extends JpaRepository<CarService, Long> {
}
