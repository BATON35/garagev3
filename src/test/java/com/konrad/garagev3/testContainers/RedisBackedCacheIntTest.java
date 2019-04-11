package com.konrad.garagev3.testContainers;

import com.konrad.garagev3.service.UserService;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.GenericContainer;

import java.time.Duration;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(initializers = RedisBackedCacheIntTest.Initializer.class)
public class RedisBackedCacheIntTest {

    @Autowired
    UserService sut;


    @ClassRule
    public static GenericContainer mysql = new GenericContainer<>("mysql:5.7")
            .withExposedPorts(3306)
            .withEnv("MYSQL_RANDOM_ROOT_PASSWORD", "true")
            .withStartupTimeout(Duration.ofSeconds(10));


    @Before
    public void setUp() {
        String address = mysql.getContainerIpAddress();
        Integer port = mysql.getFirstMappedPort();
        System.out.println("adress " + address + "\nport " + port);
//       // underTest = new Mysq(address, port);
    }

    @Test
    public void testSimplePutAndGet() {
    }

    public static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

        @Override
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            System.out.println("\n\n\n" + mysql.getContainerIpAddress() + "\n" + mysql.getMappedPort(3306));
            TestPropertyValues.of("spring.datasource.url=jdbc:mysql://"
                    + mysql.getContainerIpAddress() + ":"
                    + mysql.getMappedPort(3306) + "/login").applyTo(configurableApplicationContext);
        }
    }

//    @Test
//    public void saveUser() {
//        //given
//        UserDto userDto = UserDto.builder()
//                .email("test34@pl")
//                .password("test")
//                .name("testName")
//                .build();
//        //when
//        User user = sut.saveUser(userDto);
//        //then
//        Assert.assertEquals(sut.findUserByEmail("test34@pl").getName(), userDto.getName());
//    }
}