package com.konrad.garagev3.service;

import com.konrad.garagev3.model.dao.User;
import com.konrad.garagev3.model.dto.UserDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceIT {

    @Autowired
    UserService sut;

    @Test
    public void findUserByEmail() {
    }

    @Test
    public void saveUser() {
        //given
        UserDto userDto = UserDto.builder()
                .email("test@pl")
                .password("test")
                .name("testName")
                .build();
        //when
        User user = sut.saveUser(userDto);
        //then
        Assert.assertEquals(user.getName(), userDto.getEmail());
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