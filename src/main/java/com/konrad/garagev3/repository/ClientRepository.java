package com.konrad.garagev3.repository;

import com.konrad.garagev3.model.dao.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;


public interface ClientRepository extends JpaRepository<Client, Long> {
    Client findByEmail(String email);

    Client findBySurnameAndName(String surname, String name);

    @Query(value = " select * from client u where u.active = 1", nativeQuery = true)
    List<Client> findAllActiveClients();
  //  List<Client> findByActiveIsTrue();

    void deleteByEmail(String mail);
}
