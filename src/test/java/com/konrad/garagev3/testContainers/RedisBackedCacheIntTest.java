package com.konrad.garagev3.testContainers;

import com.konrad.garagev3.model.dao.User;
import com.konrad.garagev3.model.dto.UserDto;
import com.konrad.garagev3.service.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.GenericContainer;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisBackedCacheIntTest {

    @Autowired
    UserService sut;


    @Rule
    public GenericContainer mysql = new GenericContainer<>("mysql:5.7")
            .withExposedPorts(3316)
            .withEnv("MYSQL_RANDOM_ROOT_PASSWORD", "true");


    @Before
    public void setUp() {
        String address = mysql.getContainerIpAddress();
        Integer port = mysql.getFirstMappedPort();
       // underTest = new Mysq(address, port);
    }

    @Test
    public void testSimplePutAndGet() {
    }

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
        Assert.assertEquals(sut.findUserByEmail("test34@pl").getName(), userDto.getName());
    }
}