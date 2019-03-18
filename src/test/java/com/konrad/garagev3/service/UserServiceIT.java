package com.konrad.garagev3.service;

import com.konrad.garagev3.model.dao.User;
import com.konrad.garagev3.model.dto.UserDto;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class UserServiceIT {

    @Autowired
    UserService sut;

    @WithMockUser(username = "testName", roles = {"ADMIN"})
    @Test
    public void userWithMailNotExist() {
        Assert.assertEquals(null, sut.findUserByEmail("testEmail"));
    }

    @WithMockUser(username = "testName", roles = {"ADMIN"})
    @Test
    public void userWithMailExist() {
        Assert.assertEquals(null, sut.findUserByEmail("test@pl"));
    }

    @WithMockUser(username = "testName", roles = {"ADMIN"})
    @Test
    public void saveUser() {
        //given
        UserDto userDto = UserDto.builder()
                .email("test34@pl")
                .password("test")
                .name("testName")
                .build();
        //when
        User user = sut.saveUser(userDto);
        //then
        Assert.assertEquals(user.getEmail(), userDto.getEmail());
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