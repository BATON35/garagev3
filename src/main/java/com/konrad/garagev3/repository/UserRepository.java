package com.konrad.garagev3.repository;

import com.konrad.garagev3.model.dao.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository<T extends User> extends JpaRepository<T, Long> {
    T findByEmail(String email);

    void deleteByEmail(String email);

    List<T> findByActiveIs(int one);

    Optional<T> findByName(String username);

    Page<T> findByNameContainsOrEmailContains(String name, String email, Pageable pageable);

    @Query(value = "select * from user left join user_role on user.id = user_role.user_id where role_id is null", nativeQuery = true)
    Page<T> findByRoleIsNull(Pageable pageable);

    @Query(value = "select * from user left join user_role on user.id = user_role.user_id " +
            "where role_id is null and (email like CONCAT('%', :searchText, '%')" +
            "or name like CONCAT('%', :searchText, '%'))", nativeQuery = true)
    Page<T> findByRoleIsNullAndEmailOrNameContainsString(@Param(value = "searchText") String searchText, Pageable pageable);
}