package com.konrad.garagev3.service;

import com.konrad.garagev3.model.dao.Role;
import com.konrad.garagev3.model.dao.User;
import com.konrad.garagev3.model.dao.Workshop;
import com.konrad.garagev3.model.dto.UserDto;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.*;
@Ignore
public class UserServiceIT {
    @Autowired
    UserService userService;

//    @Autowired
//    public UserServiceIT(UserService userService) {
//        this.userService = userService;
//    }

    @Test
    public void findUserByEmail() {
        //given

        //when

        //then
    }

    @Test
    public void saveUser() {
        //given
        Role role = new Role(1, "ROLE_ADMIN");
        UserDto userDto = UserDto.builder()
                .email("test@mail")
                .name("TestName")
                .password("TestPassword")
                .roles(Stream.of(role).collect(Collectors.toSet()))
                .workshop(new Workshop())
                .build();

        //when
     //   User user = userService.saveUser(userDto);
        //then
     //   Assert.assertEquals(user, user);
    }

    @Test
    public void saveUserWithPrivileges() {
    }

    @Test
    public void findAllRoles() {
    }

    @Test
    public void findRoleById() {
    }

    @Test
    public void deleteUser() {
    }

    @Test
    public void deleteUserById() {
    }

    @Test
    public void deactivateUser() {
    }

    @Test
    public void findAllUsers() {
    }
}