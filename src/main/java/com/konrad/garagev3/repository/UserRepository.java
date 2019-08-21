package com.konrad.garagev3.repository;

import com.konrad.garagev3.model.dao.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

    void deleteByEmail(String email);

    @Query(value = " select * from user u where u.active = 1", nativeQuery = true)
    List<User> findAllActiveUsers();
}