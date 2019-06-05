package com.konrad.garagev3.repository;

import com.konrad.garagev3.model.dao.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {
    Client findClientByEmail(String email);
}
