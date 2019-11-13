package com.konrad.garagev3.repository;

import com.konrad.garagev3.model.dao.ServicePart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServicePartRepository extends JpaRepository<ServicePart, Long> {
}
