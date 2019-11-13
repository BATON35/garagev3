package com.konrad.garagev3.repository;

import com.konrad.garagev3.model.dao.Part;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PartRepository extends JpaRepository <Part, Long> {

    List<Part> findByIdIn(List<Long> ids);
}
