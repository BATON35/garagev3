package com.konrad.garagev3.repository;

import com.konrad.garagev3.model.dao.User;
import com.konrad.garagev3.model.dto.UserDto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("userRepository")
public interface UserRepository extends CrudRepository<User, Long> {
    User findByEmail(String email);
    User findUserById(int id);

    // TODO: 05.07.2019 Should this method retun eny value
    void deleteUserByEmail(String email);
    List<User> findAll();
    @Query(value = " select * from user u where u.active = 1", nativeQuery = true)
    List<User> findAllActiveUsers();
}