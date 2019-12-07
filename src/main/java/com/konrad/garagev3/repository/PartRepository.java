package com.konrad.garagev3.repository;

import com.konrad.garagev3.model.dao.Part;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PartRepository extends JpaRepository <Part, Long> {

    List<Part> findByIdIn(List<Long> ids);

    @Query(value = "select c.name from part c where c.name like CONCAT(:searchText,'%')", nativeQuery = true)
    List<String> findByAutoCompleteName(String searchText);

    List<Part> findByNameContaining(String searchText);

}
