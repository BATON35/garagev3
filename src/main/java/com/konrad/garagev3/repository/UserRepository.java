package com.konrad.garagev3.repository;

import com.konrad.garagev3.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    void deleteUserByEmail(String email);
    List<User> findAll();

}