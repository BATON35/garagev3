package com.konrad.garagev3.repository;

import com.konrad.garagev3.model.dao.Vehicle;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends CrudRepository<Vehicle, Integer> {
    Vehicle findByNumberPlate(String numberPlate);
}
