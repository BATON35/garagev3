package com.konrad.garagev3.repository;

import com.konrad.garagev3.model.dao.Client;
import com.konrad.garagev3.model.dao.Vehicle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface ClientRepository extends JpaRepository<Client, Long> {
    Client findByEmailAndDeleted(String email, boolean deleted);

    Client findBySurnameAndNameAndDeleted(String surname, String name, boolean deleted);

    void deleteByEmailAndDeleted(String mail, boolean deleted);

    Client findByVehiclesAndDeleted(Vehicle vehicle, boolean deleted);

    Page<Client> findByNameContainsOrEmailContainsAndDeleted(String name, String email, boolean deleted, Pageable pageable);

    @Query(value = "select c.email from Client c where c.email like CONCAT(:searchText,'%') and deleted = :deleted", nativeQuery = true)
    List<String> findByAutoCompleteEmail(@Param("searchText") String searchText, @Param("deleted") boolean deleted);

    @Query(value = "select c.name from Client c where c.name like CONCAT(:searchText,'%') and deleted = :deleted", nativeQuery = true)
    List<String> findByAutoCompleteName(@Param("searchText") String searchText, @Param("deleted") boolean deleted);

    Page<Client> findByDeleted(boolean Deleted, Pageable pageable);

    Optional<Client> findByIdAndDeleted(Long id, boolean deleted);
}
