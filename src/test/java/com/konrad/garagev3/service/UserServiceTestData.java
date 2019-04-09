package com.konrad.garagev3.service;

import com.konrad.garagev3.model.dao.Role;
import com.konrad.garagev3.model.dao.User;
import com.konrad.garagev3.model.dto.UserDto;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

public class UserServiceTestData {
    static Set<Role> allRoles = new LinkedHashSet<>(
            Arrays.asList(
                    new Role(1, "ROLE_ADMIN"),
                    new Role(2, "ROLE_USER"),
                    new Role(3, "ROLE_CLIENT"),
                    new Role(4, "ROLE_EMPLOYEE")
            )
    );


    public static final UserDto TEST_USER_DTO_TO_SAVE = UserDto.builder()
            .email("userToSave@pl")
            .password("test")
            .name("testName")
            .roles(allRoles)
            .build();

    public static final User TEST_USER_EXIST_IN_DATABASE = User.builder()
            .email("userExistInDatabase@pl")
            .name("exampleTester")
            .password("exampleUserPassword")
            .active(1)
            .roles(allRoles)
            .build();

    public static final UserDto TEST_USER_DTO_EXIST_IN_DATABASE = UserDto.builder()
            .email("userExistInDatabaseDto@pl")
            .name("exampleTester")
            .password("exampleUserPassword")
            .roles(allRoles)
            .build();
}
