package com.konrad.garagev3.repository;

import com.konrad.garagev3.model.dao.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByRole(String name);
}
