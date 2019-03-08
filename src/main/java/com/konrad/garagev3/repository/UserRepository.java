package com.konrad.garagev3.repository;

import com.konrad.garagev3.model.dao.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("userRepository")
public interface UserRepository extends CrudRepository<User, Long> {
    User findByEmail(String email);
    User findUserById(int id);
    void deleteUserByEmail(String email);
    void deleteUserById(int id);
    List<User> findAll();
    @Query(value = " select * from user u where u.active = 1", nativeQuery = true)
    List<User> findAllActiveUsers();

}