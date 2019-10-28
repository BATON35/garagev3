package com.konrad.garagev3.repository;

import com.konrad.garagev3.model.dao.Client;
import com.konrad.garagev3.model.dao.Vehicle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface ClientRepository extends JpaRepository<Client, Long> {
    Client findByEmail(String email);

    Client findBySurnameAndName(String surname, String name);

    List<Client> findByActiveIs(int one);

    void deleteByEmail(String mail);

    Client findByVehicles(Vehicle vehicle);

    Page<Client> findByNameContainsOrEmailContains(String name, String email, Pageable pageable);

    @Query(value = "select c.email from Client c where c.email like CONCAT(:searchText,'%')", nativeQuery = true)
    List<String> findByAutoCompleteEmail(@Param("searchText") String searchText);

    @Query(value = "select c.name from Client c where c.name like CONCAT(:searchText,'%')", nativeQuery = true)
    List<String> findByAutoCompleteName(@Param("searchText") String searchText);
}
