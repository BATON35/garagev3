package com.konrad.garagev3.repository;

import com.konrad.garagev3.model.dao.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("roleRepository")
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByRole(String role);
    Role findById(long id);
    List<Role> findAll();
}
