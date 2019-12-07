package com.konrad.garagev3.repository;

import com.konrad.garagev3.model.dao.Worker;

import java.util.List;

public interface WorkerRepository extends UserRepository<Worker> {
    List<Worker> findByNameContainingOrSurnameContaining(String name, String surname);
}
