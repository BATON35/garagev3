package com.konrad.garagev3.repository;

import com.konrad.garagev3.model.dao.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

    void deleteByEmail(String email);

    List<User> findByActiveIs(int one);

    User findByName(String username);
}