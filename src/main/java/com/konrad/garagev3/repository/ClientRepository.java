package com.konrad.garagev3.repository;

import com.konrad.garagev3.model.dao.Client;
import com.konrad.garagev3.model.dao.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ClientRepository extends JpaRepository<Client, Long> {
    Client findByEmail(String email);

    Client findBySurnameAndName(String surname, String name);

    // TODO: 22.08.2019 modify method to boolean parameter
    List<Client> findByActiveIs(int one);

    void deleteByEmail(String mail);

    Client findByVehicles(Vehicle vehicle);
}
