package com.konrad.garagev3.service;

import com.konrad.garagev3.model.dao.Role;
import com.konrad.garagev3.model.dao.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

import static com.konrad.garagev3.service.UserServiceTestData.*;
import static java.util.Objects.isNull;
import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceIT {

    @Autowired
    UserService sut;

    @Before
    public void cleanDatabase() {
        if (sut.findUserByEmail(TEST_USER_DTO_TO_SAVE.getEmail()) != null) {
            sut.deleteUser(TEST_USER_DTO_TO_SAVE.getEmail());
        }
    }

    @Before
    public void fillInDatabase() {
        if(isNull(sut.findUserByEmail(TEST_USER_DTO_EXIST_IN_DATABASE.getEmail()))) {
            sut.saveUserWithPrivileges(TEST_USER_DTO_EXIST_IN_DATABASE);
        }
        else if(sut.findUserByEmail(TEST_USER_DTO_EXIST_IN_DATABASE.getEmail()).getActive() != 1){
            sut.activateUser(sut.findUserByEmail(TEST_USER_DTO_EXIST_IN_DATABASE.getEmail()).getId());
        }
    }

    @Test
    public void userWithMailNotExist() {
        Assert.assertEquals(null, sut.findUserByEmail("mailNotExist@pl"));
    }

    @Test
    public void userWithMailExist() {
        //given
        int active = 1;
        //when
        //then
        Assert.assertEquals(TEST_USER_DTO_EXIST_IN_DATABASE.getName(),
                sut.findUserByEmail(TEST_USER_DTO_EXIST_IN_DATABASE.getEmail()).getName());
        Assert.assertEquals(TEST_USER_DTO_EXIST_IN_DATABASE.getEmail(),
                sut.findUserByEmail(TEST_USER_DTO_EXIST_IN_DATABASE.getEmail()).getEmail());
        Assert.assertEquals(active,
                sut.findUserByEmail(TEST_USER_DTO_EXIST_IN_DATABASE.getEmail()).getActive());
        Assert.assertEquals(TEST_USER_DTO_EXIST_IN_DATABASE.getRoles(),
                sut.findUserByEmail(TEST_USER_DTO_EXIST_IN_DATABASE.getEmail()).getRoles());

        assertThat(sut.findUserByEmail(TEST_USER_DTO_EXIST_IN_DATABASE.getEmail()).getActive()).isEqualTo(1);
    }

    @Test
    public void saveUser() {
        //given
        //when
        User user = sut.saveUser(TEST_USER_DTO_TO_SAVE);
        //then
        Assert.assertEquals(TEST_USER_DTO_TO_SAVE.getName(), user.getName());
        Assert.assertEquals(TEST_USER_DTO_TO_SAVE.getEmail(), user.getEmail());
        Assert.assertEquals(1, user.getActive());
        // TODO: 09.04.2019 czy tak mozna
        Assert.assertEquals(true, TEST_USER_DTO_TO_SAVE.getRoles().contains(user.getRoles().toArray()[0]));
    }

    @Test
    public void saveUserWithPrivileges() {
        //given
        //when
        // TODO: 04.04.2019 czy mozna ominąc jakies pola przy porównywaniu obiektow
        User user = sut.saveUserWithPrivileges(TEST_USER_DTO_TO_SAVE);
        //then
        Assert.assertEquals(TEST_USER_DTO_TO_SAVE.getName(), user.getName());
        Assert.assertEquals(TEST_USER_DTO_TO_SAVE.getEmail(), user.getEmail());
        Assert.assertEquals(1, user.getActive());
        Assert.assertEquals(TEST_USER_DTO_TO_SAVE.getRoles(), user.getRoles());
         Assert.assertEquals(TEST_USER_EXIST_IN_DATABASE, user);
    }

    @Test
    public void findAllRoles() {
        //given
        //when
        Set<Role> roles = new LinkedHashSet<>(sut.findAllRoles());
        //then
        Assert.assertEquals(allRoles, roles);//dlaczego dziala skoro nie ma funkcji przyjmujacej kolekcje jako parametr
    }

    @Test
    public void findRoleById() {
        //given
        //when
        Role roleId1 = sut.findRoleById(1);
        Role roleId2 = sut.findRoleById(2);
        Role roleId3 = sut.findRoleById(3);
        Role roleId4 = sut.findRoleById(4);
        //then
        Assert.assertTrue(allRoles.contains(roleId1));
        Assert.assertTrue(allRoles.contains(roleId2));
        Assert.assertTrue(allRoles.contains(roleId3));
        Assert.assertTrue(allRoles.contains(roleId4));


    }

    @Test
    public void deleteUser() {
        //given
        //when
        sut.deleteUser(TEST_USER_DTO_EXIST_IN_DATABASE.getEmail());
        //then
        Assert.assertNull(
                sut.findUserByEmail(TEST_USER_DTO_EXIST_IN_DATABASE.getEmail()));
    }

    @Test
    public void deleteUserById() {
        //given

        //when
        sut.deleteUserById(
                sut.findUserByEmail(
                        TEST_USER_DTO_EXIST_IN_DATABASE.getEmail()).getId());
        //then
        Assert.assertNull(
                sut.findUserByEmail(TEST_USER_DTO_EXIST_IN_DATABASE.getEmail()));
    }

    @Test
    public void deactivateUser() {
        //given
        //when
        sut.deactivateUser(sut.findUserByEmail(TEST_USER_DTO_EXIST_IN_DATABASE.getEmail()).getId());
        //then
        Assert.assertEquals(0,sut.findUserByEmail(TEST_USER_DTO_EXIST_IN_DATABASE.getEmail()).getActive());
    }

    @Test
    public void findAllUsers() {
    }
}