package com.konrad.garagev3.repository;

import com.konrad.garagev3.model.dao.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {
    Client findClientByEmail(String email);
    Client findClientBySurnameAndName(String surname, String name);
    @Query(value = " select * from client u where u.active = 1", nativeQuery = true)
    List<Client> findAllActiveClients();
}
