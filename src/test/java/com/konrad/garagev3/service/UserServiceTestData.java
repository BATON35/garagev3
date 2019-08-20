package com.konrad.garagev3.service;

import com.konrad.garagev3.model.dao.Role;
import com.konrad.garagev3.model.dao.User;
import com.konrad.garagev3.model.dto.UserDto;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.function.ToDoubleBiFunction;

class UserServiceTestData {
    static Set<Role> ALL_ROLES = new LinkedHashSet<>(
            Arrays.asList(
                    new Role(1, "ROLE_ADMIN"),
                    new Role(2, "ROLE_USER"),
                    new Role(3, "ROLE_EMPLOYEE")
            )
    );

    static final User TEST_USER_TO_SAVE = User.builder()
            .email("userDTOToSave@pl")
            .password("test")
            .name("testName")
            .roles(ALL_ROLES)
            .active(1)
            .build();
    static final UserDto TEST_USER_DTO_TO_SAVE = UserDto.builder()
            .email("userDTOToSave@pl")
            .password("test")
            .name("testName")
            .roles(ALL_ROLES)
            .active(1)
            .build();

    static final User TEST_USER_SAVED_IN_DATABASE = User.builder()
            .email("userToDTOSave@pl")
            .password("test")
            .name("testName")
            .roles(ALL_ROLES)
            .active(1)
            .build();

    static final UserDto TEST_USER_DTO_EXIST_IN_DATABASE = UserDto.builder()
            .email("userExistInDatabaseDto@pl")
            .name("exampleTester")
            .password("exampleUserPassword")
            .roles(ALL_ROLES)
            .active(1)
            .build();
    static final UserDto TEST_USER2_DTO_EXIST_IN_DATABASE = UserDto.builder()
            .email("user2ExistInDatabaseDto@pl")
            .name("exampleTester2")
            .password("exampleUser2Password")
            .roles(ALL_ROLES)
            .active(1)
            .build();
    static final UserDto TEST_USER3_DTO_EXIST_IN_DATABASE = UserDto.builder()
            .email("user3ExistInDatabaseDto@pl")
            .name("exampleTester3")
            .password("exampleUser3Password")
            .roles(ALL_ROLES)
            .active(1)
            .build();
    static final User TEST_USER_EXIST_IN_DATABASE = User.builder()
            .email("userExistInDatabaseDto@pl")
            .name("exampleTester")
            .password("exampleUserPassword")
            .roles(ALL_ROLES)
            .active(1)
            .build();
    static final User TEST_USER2_EXIST_IN_DATABASE = User.builder()
            .email("user2ExistInDatabaseDto@pl")
            .name("exampleTester2")
            .password("exampleUser2Password")
            .roles(ALL_ROLES)
            .active(1)
            .build();
    static final User TEST_USER3_EXIST_IN_DATABASE = User.builder()
            .email("user3ExistInDatabaseDto@pl")
            .name("exampleTester3")
            .password("exampleUser3Password")
            .roles(ALL_ROLES)
            .active(1)
            .build();
    // TODO: 12.07.2019 problems with test occurred if roles are in local variable
    static final User TEST_USER = User.builder()
            .name("user")
            .email("test@test.com")
            .roles(ALL_ROLES)
            .active(1)
            .build();
    static final User TEST_USER_1 = User.builder()
            .name("user_1")
            .email("test@test_1.com")
            .roles(ALL_ROLES)
            .active(1)
            .build();
    static final User TEST_USER_INACTIVE = User.builder()
            .name("user_inactive")
            .email("test@test_inactive.com")
            .roles(ALL_ROLES)
            .active(0)
            .build();
    static final User TEST_USER_ACTIVE = User.builder()
            .name("user_inactive")
            .email("test@test_inactive.com")
            .roles(ALL_ROLES)
            .active(1)
            .build();
    static final UserDto TEST_USER_DTO = UserDto.builder()
            .name("user")
            .email("test@test.com")
            .roles(ALL_ROLES)
            .active(1)
            .build();
    static final UserDto TEST_USER_DTO_1 = UserDto.builder()
            .name("user_1")
            .email("test@test_1.com")
            .active(1)
            .roles(ALL_ROLES)
            .build();
}
