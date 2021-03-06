package com.konrad.garagev3.repository;

import com.konrad.garagev3.model.dao.Role;
import com.konrad.garagev3.model.dao.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository<T extends User> extends JpaRepository<T, Long> {
    Optional<T> findByEmail(String email);

    Optional<T> findByIdAndDeleted(Long id, Boolean deleted);

    void deleteByEmail(String email);

    Optional<T> findByName(String userName);

    Optional<T> findByLogin(String userLogin);

    Page<T> findByNameContainsAndRolesInOrEmailContains(String name, List<Role> roles, String email, Pageable pageable);

    Page<T> findByNameContainsOrEmailContains(String name, String email, Pageable pageable);

    @Query(value = "select * from user u join user_role ur on u.id = ur.user_id join role r on ur.role_id = r.id where r.name in (:roles) and (email like CONCAT('%', :email, '%') or u.name like CONCAT('%', :name, '%')) and u.deleted = :deleted", nativeQuery = true)
    Page<T> findByRolesAndDeleted(@Param("roles") List<String> roles, @Param("email") String email,@Param("name") String name, @Param("deleted") boolean deleted, Pageable pageable);

//    @Query(value = "select * from user u join user_role ur on u.id = ur.user_id join role r on ur.role_id = r.id where r.name in (:roles) and r.name not in (:excludeRole) and (email like CONCAT('%', :email, '%') or u.name like CONCAT('%', :name, '%'))", nativeQuery = true)
//    Page<T> findByRoles(@Param("roles") List<String> roles, @Param("email") String email,@Param("name") String name, @Param("excludeRole") List<String> excludeRole, Pageable pageable);

    @Query(value = "select * from user left join user_role on user.id = user_role.user_id where role_id is null", nativeQuery = true)
    Page<T> findByRoleIsNull(Pageable pageable);

    @Query(value = "select * from user left join user_role on user.id = user_role.user_id " +
            "where role_id is null and (email like CONCAT('%', :searchText, '%')" +
            "or name like CONCAT('%', :searchText, '%')) and deleted = :deleted", nativeQuery = true)
    Page<T> findByRoleIsNullAndEmailOrNameContainsStringAndDeleted(@Param(value = "searchText") String searchText,@Param("deleted") Boolean deleted, Pageable pageable);
}
